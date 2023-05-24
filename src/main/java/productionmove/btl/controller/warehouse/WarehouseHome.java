package productionmove.btl.controller.warehouse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class WarehouseHome {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICategoryProductService iCategoryProductService;
    @Autowired
    private IProductWarehouseService iProductWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IProductAgencyService iProductAgencyService;
    @Autowired
    private ISanphamloiService iSanphamloiService;
    @Autowired
    private ILichsuService iLichsuService;


    @GetMapping("/bigcorp/cssx/{id}")
    public String index(Model model, @PathVariable String id,  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));
        List<Category> categories = iCategoryService.getAllCategory();
        List<CategoryProduct> productList = iCategoryProductService.findAll();
        for(CategoryProduct product: productList){
            List<ProductWarehouse> list1 = iProductWarehouseService.findAllByCodewarehouseAndCategoryproduct(warehouse.getCode(), product.getName());
            product.setQuantity(list1.size());
            for(ProductWarehouse p: list1){
                if(p.getNgayxuatkho() != null){
                    product.setSold(product.getSold() + 1);
                }
            }
            product.setRemaining(product.getQuantity() - product.getSold());
        }
        System.out.println(Utils.toJSONArr(productList));
        model.addAttribute("data", Utils.toJSONArr(productList));
        model.addAttribute("listC", categories);
        model.addAttribute("listP", productList);
        return "cososanxuatindex";
    }

    @GetMapping("/bigcorp/cssx/{id}/nhapkho/{code}/{status}")
    public String nhapkho(Model model, @PathVariable String id,@PathVariable String code,  @PathVariable String status, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));

        List<Category> categories = iCategoryService.getAllCategory();
        List<CategoryProduct> categoryProducts = iCategoryProductService.findAll();

        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setCategory(iCategoryProductService.getByCode(code).getCategory());
        categoryProduct.setName(iCategoryProductService.getByCode(code).getName());
        categoryProduct.setCode(code);
        if(Integer.valueOf(status) == 1){
            model.addAttribute("mess", "");
        } else if(Integer.valueOf(status) == 0){
            model.addAttribute("mess", "Vui lòng nhập số lượng");
        }
        model.addAttribute("p", categoryProduct);

        return "cssxnhapkho";
    }

    @PostMapping("/bigcorp/cssx/{id}/nhapkho/{codee}")
    public String nhapkho(Model model, @PathVariable String id,  @PathVariable String codee, HttpServletRequest request, CategoryProduct categoryProduct) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));
        if(categoryProduct.getQuantity() == 0){
            return "redirect:/bigcorp/cssx/" + id + "/nhapkho/" + codee + "/0";
        } else {
            Lichsu ls = new Lichsu();
            ls.setCodewarehouse(warehouse.getCode());
            ls.setStatus("Nhập kho");
            ls.setNamewarehouse(warehouse.getName());
            ls.setCategoryproduct(categoryProduct.getName());
            ls.setDate(Date.valueOf(LocalDate.now()));
            ls.setQuantity(categoryProduct.getQuantity());
            iLichsuService.save(ls);
            for(int i = 0; i < categoryProduct.getQuantity(); i++){
                ProductWarehouse p = new ProductWarehouse();
                p.setCategory(categoryProduct.getCategory());
                p.setCategoryproduct(categoryProduct.getName());
                p.setCodewarehouse(warehouse.getCode());
                p.setNgaysanxuat(Date.valueOf(java.time.LocalDate.now()));
                String code = Utils.randomAlphaNumeric(8);
                boolean checkCode = true;
                for(ProductWarehouse a: iProductWarehouseService.findAll()) {
                    if(a.getCode().equals(code)){
                        checkCode = false;
                    }
                }
                while(!checkCode){
                    code = Utils.randomAlphaNumeric(8);
                }
                p.setCode(code);
                iProductWarehouseService.save(p);
            }
            return "redirect:/bigcorp/cssx/" + id;
        }
    }

    @GetMapping("/bigcorp/cssx/{id}/xuatkho/{code}/{status}")
    public String xuatkho(Model model, @PathVariable String id, @PathVariable String code, @PathVariable String status, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));

        List<Agency> agencies = iAgencyService.getAllAgency();

        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setCategory(iCategoryProductService.getByCode(code).getCategory());
        categoryProduct.setName(iCategoryProductService.getByCode(code).getName());
        categoryProduct.setCode(code);
        if(Integer.valueOf(status) == 1){
            model.addAttribute("mess", "");
        } else if(Integer.valueOf(status) == 0){
            model.addAttribute("mess", "Vui lòng nhập số lượng");
        }
        model.addAttribute("p", categoryProduct);

        model.addAttribute("listA", agencies);
        return "cssxxuatkho";
    }

    @PostMapping("/bigcorp/cssx/{id}/xuatkho/{code}")
    public String xuatkho(Model model, @PathVariable String id,  @PathVariable String code, HttpServletRequest request, CategoryProduct categoryProduct) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));
        if(categoryProduct.getQuantity() == 0){
            return "redirect:/bigcorp/cssx/" + id + "/xuatkho/" + code + "/0";
        }
        Lichsu ls = new Lichsu();
        ls.setCodewarehouse(warehouse.getCode());
        ls.setNamewarehouse(warehouse.getName());
        ls.setStatus("Xuất kho");
        ls.setCategoryproduct(categoryProduct.getName());
        ls.setDate(Date.valueOf(LocalDate.now()));
        ls.setQuantity(categoryProduct.getQuantity());
        ls.setCodeagency(categoryProduct.getCode());
        Agency agency = iAgencyService.getAgencyByCode(categoryProduct.getCode());
        ls.setNameagency(agency.getName());
        iLichsuService.save(ls);

        Lichsu ls1 = new Lichsu();
        ls1.setCodewarehouse(warehouse.getCode());
        ls1.setNamewarehouse(warehouse.getName());
        ls1.setStatus("Nhập kho");
        ls1.setCategoryproduct(categoryProduct.getName());
        ls1.setDate(Date.valueOf(LocalDate.now()));
        ls1.setQuantity(categoryProduct.getQuantity());
        ls1.setCodeagency(categoryProduct.getCode());
        ls.setNameagency(agency.getName());
        iLichsuService.save(ls1);

        List<ProductWarehouse> list = iProductWarehouseService.findAllByCodewarehouseAndCategoryproduct(warehouse.getCode(), categoryProduct.getName());
        list.removeIf(p -> p.getNgayxuatkho() != null);
        for(int i = 0; i < categoryProduct.getQuantity(); i++){
            System.out.println(list.get(i).getId());
            ProductWarehouse w = new ProductWarehouse();
            w = list.get(i);
            w.setNgayxuatkho(Date.valueOf(java.time.LocalDate.now()));
            w.setCodeagency(categoryProduct.getCode());
            w.setId(list.get(i).getId());
            iProductWarehouseService.save(w);

            ProductAgency productAgency = new ProductAgency();
            productAgency.setCategory(categoryProduct.getCategory());
            productAgency.setCategoryproduct(categoryProduct.getName());
            productAgency.setNgaynhap(Date.valueOf(java.time.LocalDate.now()));
            productAgency.setCodeagency(categoryProduct.getCode());
            productAgency.setCodewarehouse(warehouse.getCode());
            productAgency.setNgaysanxuat(list.get(i).getNgaysanxuat());
            productAgency.setCode(list.get(i).getCode());

            iProductAgencyService.save(productAgency);

        }
        return "redirect:/bigcorp/cssx/" + id;
    }

    @GetMapping("/bigcorp/cssx/{id}/thongkesolieu/{cate}")
    public String thongkesolieu(Model model, @PathVariable String id, @PathVariable String cate, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));
        String title = "";
        if(cate.equals("nam")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu năm " + String.valueOf(year);
            List<CategoryProduct> list = iCategoryProductService.findAll();

            for(CategoryProduct categoryProduct: list){
                List<ProductWarehouse> listP = iProductWarehouseService.findAllByCodewarehouseAndCategoryproduct(warehouse.getCode(), categoryProduct.getName());

                String start = String.valueOf(year) + "-01-01";
                String end = String.valueOf(year) + "-12-31";

                for(ProductWarehouse p: listP){
                    if(p.getNgayxuatkho()!= null){
                        if(Utils.checkDate(start, end, String.valueOf(p.getNgayxuatkho()))){
                            categoryProduct.setSold(categoryProduct.getSold() + 1);
                        }
                    }
                    if(Utils.checkDate(start, end, String.valueOf(p.getNgaysanxuat()))){
                        categoryProduct.setRemaining(categoryProduct.getRemaining() + 1);
                    }
                }
            }
            model.addAttribute("title", title);
            model.addAttribute("list", list);
            return "thongkesanphamcssxtheonam";
        } else if(cate.equals("quy")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu từ quý I đến quý IV năm " + String.valueOf(year);
            List<CategoryProduct> list1 = iCategoryProductService.findAll();
            List<Thongketheoquy> list = new ArrayList<>();
            for(CategoryProduct cp: list1) {
                Thongketheoquy tk = new Thongketheoquy();
                tk.setName(cp.getName());
                tk.setNhapquy1(iProductWarehouseService.thongkenhapvaotheoquy(warehouse.getCode(), cp.getName(), 1).size());
                tk.setNhapquy2(iProductWarehouseService.thongkenhapvaotheoquy(warehouse.getCode(), cp.getName(), 2).size());
                tk.setNhapquy3(iProductWarehouseService.thongkenhapvaotheoquy(warehouse.getCode(), cp.getName(), 3).size());
                tk.setNhapquy4(iProductWarehouseService.thongkenhapvaotheoquy(warehouse.getCode(), cp.getName(), 4).size());

                tk.setXuatquy1(iProductWarehouseService.thongkebantheoquy(warehouse.getCode(), cp.getName(), 1).size());
                tk.setXuatquy2(iProductWarehouseService.thongkebantheoquy(warehouse.getCode(), cp.getName(), 2).size());
                tk.setXuatquy3(iProductWarehouseService.thongkebantheoquy(warehouse.getCode(), cp.getName(), 3).size());
                tk.setXuatquy4(iProductWarehouseService.thongkebantheoquy(warehouse.getCode(), cp.getName(), 4).size());
                list.add(tk);
            }
            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamcssxtheoquy";
        } else if(cate.equals("thang")){
            int year = LocalDate.now().getYear();

            title = "Thống kê số liệu từ tháng 1 đến tháng 12 năm " + String.valueOf(year);

            List<CategoryProduct> listP = iCategoryProductService.findAll();
            List<Thongketheothang> list = new ArrayList<>();
            for(CategoryProduct p: listP){
                Thongketheothang tk = new Thongketheothang();
                tk.setName(p.getName());

                tk.setNhap1(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 1).size());
                tk.setNhap2(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 2).size());
                tk.setNhap3(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 3).size());
                tk.setNhap4(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 4).size());
                tk.setNhap5(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 5).size());
                tk.setNhap6(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 6).size());
                tk.setNhap7(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 7).size());
                tk.setNhap8(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 8).size());
                tk.setNhap9(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 9).size());
                tk.setNhap10(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 10).size());
                tk.setNhap11(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 11).size());
                tk.setNhap12(iProductWarehouseService.thongkenhapvaotheothang(warehouse.getCode(), p.getName(), 12).size());

                tk.setXuat1(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 1).size());
                tk.setXuat2(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 2).size());
                tk.setXuat3(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 3).size());
                tk.setXuat4(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 4).size());
                tk.setXuat5(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 5).size());
                tk.setXuat6(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 6).size());
                tk.setXuat7(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 7).size());
                tk.setXuat8(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 8).size());
                tk.setXuat9(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 9).size());
                tk.setXuat10(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 10).size());
                tk.setXuat11(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 11).size());
                tk.setXuat12(iProductWarehouseService.thongkebantheothang(warehouse.getCode(), p.getName(), 12).size());

                list.add(tk);
            }

            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamcssxtheothang";
        }
        return "";
    }

    @GetMapping("/bigcorp/cssx/{id}/lichsu")
    public String lichsu(Model model, @PathVariable String id,  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));

        List<Lichsu> lichsuList = iLichsuService.findAllByCodewarehouse(warehouse.getCode());
        lichsuList.removeIf(ls -> ls.getStatus().equals("Nhập kho") && ls.getCodeagency() != null);
        model.addAttribute("listLS", lichsuList);
        return "cssxlichsu";
    }

    @GetMapping("/bigcorp/cssx/{id}/sanphamloi")
    public String sanphamloi(Model model, @PathVariable String id,  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Warehouse warehouse = iWarehouseService.getWarehouseById(Long.parseLong(id));

        List<Sanphamloi> sanphamlois = iSanphamloiService.findAllByCodewarehouse(warehouse.getCode());

        model.addAttribute("listP", sanphamlois);
        return "cssxsanphamloi";
    }
}
