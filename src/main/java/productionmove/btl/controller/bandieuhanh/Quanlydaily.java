package productionmove.btl.controller.bandieuhanh;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import productionmove.btl.entity.*;
import productionmove.btl.service.*;

import java.util.List;

@Controller
public class Quanlydaily {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private IProductAgencyService iProductAgencyService;
    @Autowired
    private ICategoryProductService iCategoryProductService;


    @GetMapping("/bigcorp/bandieuhanh/quanly-daily/{id}")
    public String index(Model model, @PathVariable String id, HttpServletRequest request) throws Exception {
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

        Agency agency = iAgencyService.getAgencyById(Long.parseLong(id));
        List<ProductAgency> productList = iProductAgencyService.findAllByAgency(agency.getCode());
        List<CategoryProduct> listCategoryProducts = iCategoryProductService.findAll();
        for(CategoryProduct cp: listCategoryProducts){
            for(ProductAgency p: productList){
                if(p.getCategoryproduct().equals(cp.getName())){
                    cp.setQuantity(cp.getQuantity() + 1);
                    if(p.getNgayban() != null){
                        cp.setSold(cp.getSold() + 1);
                    }
                    cp.setRemaining(cp.getQuantity() - cp.getSold());
                }
            }
        }
        model.addAttribute("listProduct", listCategoryProducts);

        return "quanlydaily";
    }


}
