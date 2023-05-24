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
public class Quanlycososanxuat {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private IProductWarehouseService iProductWarehouseService;
    @Autowired
    private ICategoryProductService iCategoryProductService;

    @GetMapping("/bigcorp/bandieuhanh/quanly-cososanxuat/{id}")
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

        Long idCoso = Long.parseLong(id);
        Warehouse warehouse = iWarehouseService.getWarehouseById(idCoso);
        List<ProductWarehouse> listProduct = iProductWarehouseService.findAllByWarehouse(warehouse.getCode());
        List<CategoryProduct> listCategoryProducts = iCategoryProductService.findAll();
        for(CategoryProduct cp: listCategoryProducts){
            for(ProductWarehouse p: listProduct){
                if(p.getCategoryproduct().equals(cp.getName())){
                    cp.setQuantity(cp.getQuantity() + 1);
                }
            }
        }
        model.addAttribute("listProduct", listCategoryProducts);

        return "quanlycososanxuat";
    }
}
