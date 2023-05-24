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
public class Quanlytrungtambaohanh {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @Autowired
    private IProductServiceCenterService iProductServiceCenterService;
    @Autowired
    private ICategoryProductService iCategoryProductService;

    @GetMapping("/bigcorp/bandieuhanh/quanly-trungtambaohanh/{id}")
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

        ServiceCenter serviceCenter = iServiceCenterService.getServiceCenterById(Long.parseLong(id));
        List<ProductServiceCenter> productList = iProductServiceCenterService.findAllByServiceCenter(serviceCenter.getCode());
        List<CategoryProduct> categoryProductList = iCategoryProductService.findAll();
        for(CategoryProduct cp: categoryProductList){
            for(ProductServiceCenter p: productList){
                if(p.getCategoryproduct().equals(cp.getName())){
                    cp.setQuantity(cp.getQuantity() + 1);
                    if(p.getStatus().equals("Đã hoàn thành") || p.getStatus().equals("Trả về cơ sở sản xuất")){
                        cp.setSold(cp.getSold() + 1);
                    }
                }
                cp.setRemaining(cp.getQuantity() - cp.getSold());
            }
        }

        model.addAttribute("listProduct", categoryProductList);

        return "quanlytrungtambaohanh";
    }
}
