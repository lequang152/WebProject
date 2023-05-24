package productionmove.btl.controller.bandieuhanh;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import productionmove.btl.entity.Agency;
import productionmove.btl.entity.ServiceCenter;
import productionmove.btl.entity.Warehouse;
import productionmove.btl.model.Office;
import productionmove.btl.service.IAgencyService;
import productionmove.btl.service.IServiceCenterService;
import productionmove.btl.service.IWarehouseService;
import productionmove.btl.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BigCorpHome {
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;

    @GetMapping("/bigcorp/bandieuhanh")
    public String home(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Office> list = new ArrayList<>();
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        for(Warehouse a: warehouses){
            Office o = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), "warehouse");
            list.add(o);
        }
        List<Agency> agencies = iAgencyService.getAllAgency();
        for(Agency a: agencies){
            Office o = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), "warehouse");
            list.add(o);
        }
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        for(ServiceCenter a: serviceCenters){
            Office o = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), "warehouse");
            list.add(o);
        }

        model.addAttribute("listWarehouse",warehouses);
        model.addAttribute("listAgency",agencies);
        model.addAttribute("listServiceCenter",serviceCenters);
        model.addAttribute("listOffice", list);
        return "bigcorpindex";
    }

    @GetMapping("/bigcorp/bandieuhanh/suacoso/{category}/{code}")
    public String edit(Model model, HttpServletRequest request, @PathVariable String category, @PathVariable String code){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        List<Agency> agencies = iAgencyService.getAllAgency();
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        Office office = null;
        if(category.equals("warehouse")){
            Warehouse a = iWarehouseService.getWarehouseByCode(code);
            office = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), category);
        } else if(category.equals("agency")){
            Agency a = iAgencyService.getAgencyByCode(code);
            office = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), category);
        } else if(category.equals("servicecenter")){
            ServiceCenter a = iServiceCenterService.getServiceCenterByCode(code);
            office = new Office(a.getCode(), a.getName(), a.getAddress(), a.getManager(), a.getPhone(), category);
        }


        model.addAttribute("listWarehouse",warehouses);
        model.addAttribute("listAgency",agencies);
        model.addAttribute("listServiceCenter",serviceCenters);
        model.addAttribute("office", office);
        model.addAttribute("cate", category);

        return "suacoso";
    }

    @PostMapping("/bigcorp/bandieuhanh/suacoso/{cate}/{code}")
    public String edition(Model model, HttpServletRequest request,@PathVariable String cate,  @PathVariable String code, Office office){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        if(cate.equals("warehouse")){
            Warehouse a = iWarehouseService.getWarehouseByCode(code);
            Warehouse w = new Warehouse(a.getId(), code, office.getName(), office.getAddress(), office.getManager(),office.getPhone());
            iWarehouseService.upadteWarehouse(w);
        } else if(cate.equals("agency")){
            Agency a = iAgencyService.getAgencyByCode(code);
            Agency w = new Agency(a.getId(), code, office.getName(), office.getAddress(), office.getManager(),office.getPhone());
        } else if(cate.equals("servicecenter")){
            ServiceCenter a = iServiceCenterService.getServiceCenterByCode(code);
            ServiceCenter w = new ServiceCenter(a.getId(), code, office.getName(), office.getAddress(), office.getManager(),office.getPhone());
        }

        return "redirect:/bigcorp/bandieuhanh";
    }

    @GetMapping("/bigcorp/bandieuhanh/themmoi")
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

        Office office = new Office();
        model.addAttribute("office", office);

        return "themcoso";
    }

    @PostMapping("/bigcorp/bandieuhanh/themmoi")
    public String add(Model model, HttpServletRequest request, Office office){
        System.out.println(office.getCategory());
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }

        String code = "";

        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        List<Agency> agencies = iAgencyService.getAllAgency();
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        boolean checkCode = true;
        if(office.getCategory().equals("warehouse")) {
            Warehouse w = new Warehouse();
            code += "WH" + Utils.randomAlphaNumeric(5);
            for(Warehouse a: warehouses) {
                if(a.getCode().equals(code)){
                    checkCode = false;
                }
            }
            while(!checkCode){
                code += "WH" + Utils.randomAlphaNumeric(5);
            }
            w.setCode(code);
            w.setName(office.getName());
            w.setAddress(office.getAddress());
            w.setManager(office.getManager());
            w.setPhone(office.getPhone());
            iWarehouseService.addWarehouse(w);
        } else if(office.getCategory().equals("agency")) {
            Agency w = new Agency();
            code += "AG" + Utils.randomAlphaNumeric(5);
            for(Agency a: agencies) {
                if(a.getCode().equals(code)){
                    checkCode = false;
                }
            }
            while(!checkCode){
                code += "AG" + Utils.randomAlphaNumeric(5);
            }
            w.setCode(code);
            w.setCode(code);
            w.setName(office.getName());
            w.setAddress(office.getAddress());
            w.setManager(office.getManager());
            w.setPhone(office.getPhone());
            iAgencyService.addAgency(w);
        } else if(office.getCategory().equals("servicecenter")) {
            ServiceCenter w = new ServiceCenter();
            code += "SC" + Utils.randomAlphaNumeric(5);
            for(ServiceCenter a: serviceCenters) {
                if(a.getCode().equals(code)){
                    checkCode = false;
                }
            }
            while(!checkCode){
                code += "SC" + Utils.randomAlphaNumeric(5);
            }
            w.setCode(code);
            w.setCode(code);
            w.setName(office.getName());
            w.setAddress(office.getAddress());
            w.setManager(office.getManager());
            w.setPhone(office.getPhone());
            iServiceCenterService.addServiceCenter(w);
        }
        return "redirect:/bigcorp/bandieuhanh";
    }

    @GetMapping("/bigcorp/bandieuhanh/xoacoso/{cate}/{code}")
    public String delete(Model model, HttpServletRequest request,@PathVariable String cate,  @PathVariable String code){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        if(cate.equals("warehouse")){
            Warehouse a = iWarehouseService.getWarehouseByCode(code);
            iWarehouseService.deleteWarehouse(a.getId());
        } else if(cate.equals("agency")){
            Agency a = iAgencyService.getAgencyByCode(code);
            iAgencyService.deleteAgency(a.getId());
        } else if(cate.equals("servicecenter")){
            ServiceCenter a = iServiceCenterService.getServiceCenterByCode(code);
            iServiceCenterService.deleteServiceCenter(a.getId());
        }

        return "redirect:/bigcorp/bandieuhanh";
    }
}
