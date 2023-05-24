package productionmove.btl.controller.trungtambaohanh;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import productionmove.btl.entity.*;
import productionmove.btl.model.Thongketheoquy;
import productionmove.btl.model.Thongketheothang;
import productionmove.btl.service.*;
import productionmove.btl.utils.Utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Trungtambaohanh {
    @Autowired
    private IProductServiceCenterService iProductServiceCenterService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private ICategoryProductService iCategoryProductService;
    @Autowired
    private IProductAgencyService iProductAgencyService;
    @Autowired
    private ISanphamloiService iSanphamloiService;


    @GetMapping("/bigcorp/trungtambaohanh/{id}")
    public String index(Model model, @PathVariable String id, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        ServiceCenter serviceCenter = iServiceCenterService.getServiceCenterById(Long.parseLong(id));
        List<ProductServiceCenter> listP = iProductServiceCenterService.findAllByServiceCenter(serviceCenter.getCode());

        model.addAttribute("listP", listP);

        return "trungtambaohanhindex";
    }

    @GetMapping("/bigcorp/trungtambaohanh/{id}/{status}/{code}")
    public String index(Model model, @PathVariable String id, @PathVariable String status, @PathVariable String code, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        ServiceCenter serviceCenter = iServiceCenterService.getServiceCenterById(Long.parseLong(id));
        List<ProductServiceCenter> listP = iProductServiceCenterService.findAllByServiceCenter(serviceCenter.getCode());
        ProductServiceCenter productServiceCenter = new ProductServiceCenter();
        for(ProductServiceCenter p: listP){
            if(p.getCode().equals(code)){
                productServiceCenter.setId(p.getId());
                productServiceCenter.setQuantity(p.getQuantity());
                productServiceCenter.setCategory(p.getCategory());
                productServiceCenter.setCategoryproduct(p.getCategoryproduct());
                productServiceCenter.setCode(code);
                productServiceCenter.setCodeagency(p.getCodeagency());
                productServiceCenter.setCodewarehouse(p.getCodewarehouse());
                productServiceCenter.setCodeservicecenter(p.getCodeservicecenter());
                productServiceCenter.setCodecustomer(p.getCodecustomer());
                productServiceCenter.setNgaynhan(p.getNgaynhan());
                productServiceCenter.setMabaohanh(p.getMabaohanh());
                productServiceCenter.setPhonecustomer(p.getPhonecustomer());
                productServiceCenter.setNamecustomer(p.getNamecustomer());
                productServiceCenter.setNameerror(p.getNameerror());
                productServiceCenter.setNameagency(p.getNameagency());
                if(status.equals("hoanthanh")){
                    productServiceCenter.setStatus("Đã hoàn thành");
                    productServiceCenter.setNgaytra(Date.valueOf(java.time.LocalDate.now()));
                } else if(status.equals("loi")){
                    productServiceCenter.setStatus("Trả về cơ sở sản xuất");
                    productServiceCenter.setNgaytra(Date.valueOf(java.time.LocalDate.now()));
                }
            }
        }
        iProductServiceCenterService.save(productServiceCenter);
        if(productServiceCenter.getStatus().equals("Trả về cơ sở sản xuất")){
            Sanphamloi sp = new Sanphamloi();
            sp.setCategoryproduct(productServiceCenter.getCategoryproduct());
            sp.setCodeagency(productServiceCenter.getCodeagency());
            sp.setCodewarehouse(productServiceCenter.getCodewarehouse());
            sp.setCode(productServiceCenter.getCode());
            List<ProductAgency> productAgency = iProductAgencyService.findAllByAgency(productServiceCenter.getCodeagency());
            for(ProductAgency p: productAgency){
                if(p.getCode().equals(productServiceCenter.getCode())){
                    sp.setNgaysanxuat(p.getNgaysanxuat());
                }
            }
            sp.setNameerror(productServiceCenter.getNameerror());
            sp.setCodeservicecenter(productServiceCenter.getCodeservicecenter());
            sp.setNgaynhan(Date.valueOf(LocalDate.now()));
            sp.setNameservicecenter(iServiceCenterService.getServiceCenterByCode(productServiceCenter.getCodeservicecenter()).getName());
            iSanphamloiService.save(sp);
        }
        model.addAttribute("listP", listP);

        return "trungtambaohanhindex";
    }

    @GetMapping("/bigcorp/trungtambaohanh/{id}/thongkesolieu/{cate}")
    public String thongkesolieu(Model model, @PathVariable String id, @PathVariable String cate, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        ServiceCenter serviceCenter = iServiceCenterService.getServiceCenterById(Long.parseLong(id));
        String title = "";
        if(cate.equals("nam")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu năm " + String.valueOf(year);
            List<CategoryProduct> list = iCategoryProductService.findAll();

            for(CategoryProduct categoryProduct: list){
                List<ProductServiceCenter> listP = iProductServiceCenterService.findAllByServiceCenter(serviceCenter.getCode());
                listP.removeIf(a -> !a.getCategoryproduct().equals(categoryProduct.getName()));

                String start = String.valueOf(year) + "-01-01";
                String end = String.valueOf(year) + "-12-31";

                for(ProductServiceCenter p: listP){
                    if(p.getNgaytra()!= null){
                        if(Utils.checkDate(start, end, String.valueOf(p.getNgaytra()))){
                            categoryProduct.setSold(categoryProduct.getSold() + 1);
                        }
                    }
                    if(Utils.checkDate(start, end, String.valueOf(p.getNgaynhan()))){
                        categoryProduct.setRemaining(categoryProduct.getRemaining() + 1);
                    }
                }
            }
            model.addAttribute("title", title);
            model.addAttribute("list", list);
            return "thongkesanphamttbhtheonam";
        } else if(cate.equals("quy")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu từ quý I đến quý IV năm " + String.valueOf(year);
            List<CategoryProduct> list1 = iCategoryProductService.findAll();
            List<Thongketheoquy> list = new ArrayList<>();
            for(CategoryProduct cp: list1) {
                Thongketheoquy tk = new Thongketheoquy();
                tk.setName(cp.getName());
                tk.setNhapquy1(iProductServiceCenterService.thongkenhapvaotheoquy(serviceCenter.getCode(), cp.getName(), 1).size());
                tk.setNhapquy2(iProductServiceCenterService.thongkenhapvaotheoquy(serviceCenter.getCode(), cp.getName(), 2).size());
                tk.setNhapquy3(iProductServiceCenterService.thongkenhapvaotheoquy(serviceCenter.getCode(), cp.getName(), 3).size());
                tk.setNhapquy4(iProductServiceCenterService.thongkenhapvaotheoquy(serviceCenter.getCode(), cp.getName(), 4).size());

                tk.setXuatquy1(iProductServiceCenterService.thongkebantheoquy(serviceCenter.getCode(), cp.getName(), 1).size());
                tk.setXuatquy2(iProductServiceCenterService.thongkebantheoquy(serviceCenter.getCode(), cp.getName(), 2).size());
                tk.setXuatquy3(iProductServiceCenterService.thongkebantheoquy(serviceCenter.getCode(), cp.getName(), 3).size());
                tk.setXuatquy4(iProductServiceCenterService.thongkebantheoquy(serviceCenter.getCode(), cp.getName(), 4).size());
                list.add(tk);
            }
            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamttbhtheoquy";
        } else if(cate.equals("thang")){
            int year = LocalDate.now().getYear();

            title = "Thống kê số liệu từ tháng 1 đến tháng 12 năm " + String.valueOf(year);

            List<CategoryProduct> listP = iCategoryProductService.findAll();
            List<Thongketheothang> list = new ArrayList<>();
            for(CategoryProduct p: listP){
                Thongketheothang tk = new Thongketheothang();
                tk.setName(p.getName());

                tk.setNhap1(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 1).size());
                tk.setNhap2(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 2).size());
                tk.setNhap3(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 3).size());
                tk.setNhap4(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 4).size());
                tk.setNhap5(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 5).size());
                tk.setNhap6(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 6).size());
                tk.setNhap7(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 7).size());
                tk.setNhap8(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 8).size());
                tk.setNhap9(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 9).size());
                tk.setNhap10(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 10).size());
                tk.setNhap11(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 11).size());
                tk.setNhap12(iProductServiceCenterService.thongkenhapvaotheothang(serviceCenter.getCode(), p.getName(), 12).size());

                tk.setXuat1(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 1).size());
                tk.setXuat2(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 2).size());
                tk.setXuat3(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 3).size());
                tk.setXuat4(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 4).size());
                tk.setXuat5(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 5).size());
                tk.setXuat6(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 6).size());
                tk.setXuat7(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 7).size());
                tk.setXuat8(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 8).size());
                tk.setXuat9(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 9).size());
                tk.setXuat10(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 10).size());
                tk.setXuat11(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 11).size());
                tk.setXuat12(iProductServiceCenterService.thongkebantheothang(serviceCenter.getCode(), p.getName(), 12).size());

                list.add(tk);
            }

            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamttbhtheothang";
        }
        return "";
    }
}
