package productionmove.btl.controller;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import productionmove.btl.entity.Account;
import productionmove.btl.entity.Agency;
import productionmove.btl.entity.ServiceCenter;
import productionmove.btl.entity.Warehouse;
import productionmove.btl.service.IAccountService;
import productionmove.btl.service.IAgencyService;
import productionmove.btl.service.IServiceCenterService;
import productionmove.btl.service.IWarehouseService;

import java.util.List;

@Controller
public class BigCorp {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;

    @GetMapping("/bigcorp")
    public String index(Model model){
        return "redirect:/dang-nhap";
    }

    @GetMapping("/bigcorp/suataikhoan/{id}")
    public String  editAcc(Model model, @PathVariable String id, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        model.addAttribute("listWarehouse",warehouses);

        Long idAcc = Long.parseLong(id);
        Account acc = iAccountService.getAccById(idAcc);
        acc.setId(idAcc);
        System.out.println(acc.getId());
        model.addAttribute("account", acc);
        return "suataikhoan";
    }

    @PostMapping("/bigcorp/suataikhoan/{id}")
    public String save(Model model, Account account, HttpServletRequest request, @PathVariable String id){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        account.setId(Long.parseLong(id));
        iAccountService.upadteAcc(account);
        session.setAttribute("acc", account);
        Agency agency = new Agency();
        Warehouse warehouse = new Warehouse();
        ServiceCenter serviceCenter = new ServiceCenter();
        for(Warehouse w: iWarehouseService.getAllWarehouse()){
            if(w.getName().equals(account.getOffice())){
                warehouse.setId(w.getId());
            }
        }
        for(Agency w: iAgencyService.getAllAgency()){
            if(w.getName().equals(account.getOffice())){
                agency.setId(w.getId());
            }
        }for(ServiceCenter w: iServiceCenterService.getAllServiceCenter()){
            if(w.getName().equals(account.getOffice())){
                serviceCenter.setId(w.getId());
            }
        }
        if(account.getOffice().equals("Ban điều hành")){
            return "redirect:/bigcorp/bandieuhanh";
        } else if(agency.getId() != null) {
            return "redirect:/bigcorp/daily/" + agency.getId();
        } else if(warehouse.getId() != null) {
            return "redirect:/bigcorp/cssx/" + warehouse.getId();
        } else if(serviceCenter.getId() != null) {
            return "redirect:/bigcorp/trungtambaohanh/" + serviceCenter.getId();
        }
        return null;
    }
}
