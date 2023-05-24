package productionmove.btl.controller.bandieuhanh;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import productionmove.btl.entity.*;
import productionmove.btl.service.*;

import java.util.List;

@Controller
public class Quanlydongsanpham {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICategoryProductService iCategoryProductService;

    @GetMapping("/bigcorp/bandieuhanh/quanly-dongsanpham")
    public String index(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);

        List<Category> cate = iCategoryService.getAllCategory();
        List<CategoryProduct> categoryProducts = iCategoryProductService.findAll();

        model.addAttribute("categories", cate);
        model.addAttribute("categoryProducts", categoryProducts);

        return "quanlydongsanpham";
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/themmoisanpham")
    public String add(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);

        List<Category> cate = iCategoryService.getAllCategory();
        CategoryProduct categoryProduct = new CategoryProduct();

        model.addAttribute("listCate", cate);
        model.addAttribute("categoryProduct", categoryProduct);

        return "themdongsanpham";
    }

    @PostMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/themmoi")
    public String add(Model model, HttpServletRequest request, CategoryProduct categoryProduct){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);
        iCategoryProductService.add(categoryProduct);

        return "redirect:/bigcorp/bandieuhanh/quanly-dongsanpham";
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/suasanpham/{id}")
    public String suasanpham(Model model, HttpServletRequest request, @PathVariable String id){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);

        List<Category> cate = iCategoryService.getAllCategory();
        CategoryProduct categoryProduct = iCategoryProductService.getById(Long.parseLong(id));
        categoryProduct.setId(Long.parseLong(id));
        model.addAttribute("listCate", cate);
        model.addAttribute("categoryProduct", categoryProduct);

        return "suadongsanpham";
    }

    @PostMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/suasanpham/{id}")
    public String suasanpham(Model model, HttpServletRequest request, CategoryProduct categoryProduct, @PathVariable String id){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);
        categoryProduct.setId(Long.parseLong(id));
        iCategoryProductService.add(categoryProduct);

        return "redirect:/bigcorp/bandieuhanh/quanly-dongsanpham";
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/xoasanpham/{id}")
    public String xoasanpham(Model model, HttpServletRequest request, @PathVariable String id){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        iCategoryProductService.delete(Long.parseLong(id));

        return "redirect:/bigcorp/bandieuhanh/quanly-dongsanpham";
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/themmoiloaisp")
    public String addCategory(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);


        Category category = new Category();


        model.addAttribute("category", category);

        return "themmoiloaisp";
    }

    @PostMapping("/bigcorp/bandieuhanh/quanly-dongsanpham/themmoiloaisp")
    public String addCategory(Model model, HttpServletRequest request, Category cate){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);
        List<Agency> agencies = iAgencyService.getAllAgency();
        model.addAttribute("listAgency",agencies);
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        model.addAttribute("listServiceCenter",serviceCenters);
        iCategoryService.addCategory(cate);

        return "redirect:/bigcorp/bandieuhanh/quanly-dongsanpham";
    }

}
