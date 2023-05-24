package productionmove.btl.controller.agency;


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
public class AgencyHome {
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private IProductAgencyService iProductAgencyService;
    @Autowired
    private ICategoryProductService iCategoryProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IProductServiceCenterService iProductServiceCenterService;
    @Autowired
    private ILichsuService iLichsuService;


    @GetMapping("/bigcorp/daily/{id}")
    public String index(Model model, @PathVariable String id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        List<CategoryProduct> productList = iCategoryProductService.findAll();
        for(CategoryProduct c: productList){
            List<ProductAgency> listP = iProductAgencyService.findAllByCategoryAndAgency(c.getName(), agency.getCode());
            c.setQuantity(listP.size());
            for(ProductAgency p: listP){
                if(p.getNgayban()!= null){
                    c.setSold(c.getSold() + 1);
                }
            }
            c.setRemaining(c.getQuantity() - c.getSold());
        }

        model.addAttribute("listP", productList);
        return "dailyindex";
    }

    @GetMapping("/bigcorp/daily/{id}/xuathoadon/{code}")
    public String xuathoadon(Model model, @PathVariable String id,@PathVariable String code, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        Customer customer = new Customer();
        customer.setCategory(iCategoryProductService.getByCode(code).getCategory());
        customer.setCategoryProduct(iCategoryProductService.getByCode(code).getName());
        customer.setPrice(iCategoryProductService.getByCode(code).getPrice());
        model.addAttribute("customer", customer);
        return "xuathoadon";
    }

    @PostMapping ("/bigcorp/daily/{id}/xuathoadon")
    public String xuathoadon(Model model, @PathVariable String id, HttpServletRequest request, Customer customer) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        customer.setCodeagency(agency.getCode());
        customer.setTotal(customer.getQuantity() * customer.getPrice());
        customer.setDate(Date.valueOf(java.time.LocalDate.now()));
        customer.setId(null);

        List<Customer> listCustomers = iCustomerService.getAllCustomer();
        String code = "HD" + Utils.randomAlphaNumeric(5);
        boolean checkCode = true;
        for(Customer a: listCustomers) {
            if(a.getCode().equals(code)){
                checkCode = false;
            }
        }
        if(!checkCode) {
            code = "HD" + Utils.randomAlphaNumeric(5);
        } else {
            customer.setCode(code);
        }
        iCustomerService.addCustomer(customer);


        CategoryProduct categoryProduct = iCategoryProductService.getByName(customer.getCategoryProduct());
        List<ProductAgency> list = iProductAgencyService.findAllByCategoryAndAgencyAndCodecustomer(categoryProduct.getName(), agency.getCode(), null);
        for(int i = 0; i < customer.getQuantity(); i++) {
            list.get(i).setCodecustomer(customer.getCode());
            list.get(i).setNgayban(Date.valueOf(java.time.LocalDate.now()));
            iProductAgencyService.save(list.get(i));
        }

        return "redirect:/bigcorp/daily/" + id;
    }
    @GetMapping("/bigcorp/daily/{id}/baohanh")
    public String baohanh(Model model, @PathVariable String id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        List<ProductServiceCenter> listP = new ArrayList<>();
        listP.addAll(iProductServiceCenterService.findAllByCodeagencyAndStatus(agency.getCode(), "Đã hoàn thành"));
        listP.addAll(iProductServiceCenterService.findAllByCodeagencyAndStatus(agency.getCode(), "Đang bảo hành"));
        listP.addAll(iProductServiceCenterService.findAllByCodeagencyAndStatus(agency.getCode(), "Trả về cơ sở sản xuất"));

        List<ServiceCenter> centerList = iServiceCenterService.getAllServiceCenter();

        ProductServiceCenter p = new ProductServiceCenter();

        model.addAttribute("productServiceCenter", p);
        model.addAttribute("listSC", centerList);
        model.addAttribute("listP", listP);
        return "dailybaohanh";
    }

    @PostMapping("/bigcorp/daily/{id}/baohanh")
    public String baohanh(Model model, @PathVariable String id, HttpServletRequest request, ProductServiceCenter productServiceCenter) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        String code = "BH" + Utils.randomAlphaNumeric(5);
        boolean checkCode = true;

        for(ProductServiceCenter p: iProductServiceCenterService.findAll()){
            if(p.getMabaohanh().equals(code)){
                checkCode = false;
            }
        }
        while(!checkCode){
            code = "BH" + Utils.randomAlphaNumeric(5);
        }
        productServiceCenter.setMabaohanh(code);
        for(ProductAgency p: iProductAgencyService.findAllByAgency(iAgencyService.getAgencyById(Long.parseLong(id)).getCode())){
            if(p.getCode().equals(productServiceCenter.getCode())){
                productServiceCenter.setCodewarehouse(p.getCodewarehouse());
            }
        }
        Customer customer = iCustomerService.getCustomerByCode(productServiceCenter.getCodecustomer());
        productServiceCenter.setNamecustomer(customer.getName());
        productServiceCenter.setPhonecustomer(customer.getPhone());
        productServiceCenter.setCodeagency(iAgencyService.getAgencyById(Long.parseLong(id)).getCode());
        productServiceCenter.setCategoryproduct(customer.getCategoryProduct());
        productServiceCenter.setCategory(customer.getCategory());
        productServiceCenter.setQuantity(1);
        for(ProductServiceCenter p: iProductServiceCenterService.findAll()){
            if(p.getCode().equals(productServiceCenter.getCode())){
                productServiceCenter.setQuantity(productServiceCenter.getQuantity() + 1);
            }
        }
        productServiceCenter.setNameagency(iAgencyService.getAgencyById(Long.parseLong(id)).getName());
        productServiceCenter.setNgaynhan(Date.valueOf(java.time.LocalDate.now()));
        productServiceCenter.setId(null);
        productServiceCenter.setStatus("Đang bảo hành");
        iProductServiceCenterService.save(productServiceCenter);
        return "redirect:/bigcorp/daily/" +  id + "/baohanh";
    }

    @GetMapping("/bigcorp/daily/{id}/hoadon/{code}")
    public String baohanh(Model model, @PathVariable String id, @PathVariable String code, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        CategoryProduct cp = iCategoryProductService.getByCode(code);
        List<Customer> customers = iCustomerService.getAllCustomerByCodeagency(agency.getCode());
        if(customers != null){
            customers.removeIf(c -> !c.getCategoryProduct().equals(cp.getName()));
        }
        model.addAttribute("listCustomer", customers);
        return "quanlyhoadon";
    }

    @GetMapping("/bigcorp/daily/{id}/thongkesolieu/{cate}")
    public String thongkesolieu(Model model, @PathVariable String id, @PathVariable String cate, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));

        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        String title = "";
        if(cate.equals("nam")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu năm " + String.valueOf(year);
            List<CategoryProduct> list = iCategoryProductService.findAll();

            for(CategoryProduct categoryProduct: list){
                List<ProductAgency> listP = iProductAgencyService.findAllByCategoryAndAgency(categoryProduct.getName(), agency.getCode());

                String start = String.valueOf(year) + "-01-01";
                String end = String.valueOf(year) + "-12-31";

                for(ProductAgency p: listP){
                    if(p.getNgayban()!= null){
                        if(Utils.checkDate(start, end, String.valueOf(p.getNgayban()))){
                            categoryProduct.setSold(categoryProduct.getSold() + 1);
                        }
                    }
                    if(Utils.checkDate(start, end, String.valueOf(p.getNgaynhap()))){
                        categoryProduct.setRemaining(categoryProduct.getRemaining() + 1);
                    }
                }
            }
            model.addAttribute("title", title);
            model.addAttribute("list", list);
            return "thongkesanphamdailytheonam";
        } else if(cate.equals("quy")){
            int year = LocalDate.now().getYear();
            title = "Thống kê số liệu từ quý I đến quý IV năm " + String.valueOf(year);
            List<CategoryProduct> list1 = iCategoryProductService.findAll();
            List<Thongketheoquy> list = new ArrayList<>();
            for(CategoryProduct cp: list1) {
                Thongketheoquy tk = new Thongketheoquy();
                tk.setName(cp.getName());
                tk.setNhapquy1(iProductAgencyService.thongkenhapvaotheoquy(agency.getCode(), cp.getName(), 1).size());
                tk.setNhapquy2(iProductAgencyService.thongkenhapvaotheoquy(agency.getCode(), cp.getName(), 2).size());
                tk.setNhapquy3(iProductAgencyService.thongkenhapvaotheoquy(agency.getCode(), cp.getName(), 3).size());
                tk.setNhapquy4(iProductAgencyService.thongkenhapvaotheoquy(agency.getCode(), cp.getName(), 4).size());

                tk.setXuatquy1(iProductAgencyService.thongkebantheoquy(agency.getCode(), cp.getName(), 1).size());
                tk.setXuatquy2(iProductAgencyService.thongkebantheoquy(agency.getCode(), cp.getName(), 2).size());
                tk.setXuatquy3(iProductAgencyService.thongkebantheoquy(agency.getCode(), cp.getName(), 3).size());
                tk.setXuatquy4(iProductAgencyService.thongkebantheoquy(agency.getCode(), cp.getName(), 4).size());
                list.add(tk);
            }
            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamdailytheoquy";
        } else if(cate.equals("thang")){
            int year = LocalDate.now().getYear();

            title = "Thống kê số liệu từ tháng 1 đến tháng 12 năm " + String.valueOf(year);

            List<CategoryProduct> listP = iCategoryProductService.findAll();
            List<Thongketheothang> list = new ArrayList<>();
            for(CategoryProduct p: listP){
                Thongketheothang tk = new Thongketheothang();
                tk.setName(p.getName());

                tk.setNhap1(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 1).size());
                tk.setNhap2(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 2).size());
                tk.setNhap3(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 3).size());
                tk.setNhap4(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 4).size());
                tk.setNhap5(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 5).size());
                tk.setNhap6(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 6).size());
                tk.setNhap7(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 7).size());
                tk.setNhap8(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 8).size());
                tk.setNhap9(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 9).size());
                tk.setNhap10(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 10).size());
                tk.setNhap11(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 11).size());
                tk.setNhap12(iProductAgencyService.thongkenhapvaotheothang(agency.getCode(), p.getName(), 12).size());

                tk.setXuat1(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 1).size());
                tk.setXuat2(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 2).size());
                tk.setXuat3(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 3).size());
                tk.setXuat4(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 4).size());
                tk.setXuat5(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 5).size());
                tk.setXuat6(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 6).size());
                tk.setXuat7(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 7).size());
                tk.setXuat8(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 8).size());
                tk.setXuat9(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 9).size());
                tk.setXuat10(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 10).size());
                tk.setXuat11(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 11).size());
                tk.setXuat12(iProductAgencyService.thongkebantheothang(agency.getCode(), p.getName(), 12).size());

                list.add(tk);
            }

            model.addAttribute("list", list);
            model.addAttribute("title", title);
            return "thongkesanphamdailytheothang";
        }
        return "";
    }

    @GetMapping("/bigcorp/daily/{id}/lichsu")
    public String lichsu(Model model, @PathVariable String id,  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        session.setAttribute("id", Long.parseLong(id));
        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));

        List<Lichsu> lichsuList = iLichsuService.findAllByCodeagency(agency.getCode());
        lichsuList.removeIf(ls -> ls.getStatus().equals("Xuất kho"));

        model.addAttribute("listLS", lichsuList);
        return "dailylichsu";
    }

}
