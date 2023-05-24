package productionmove.btl.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import productionmove.btl.entity.Account;
import productionmove.btl.entity.Agency;
import productionmove.btl.entity.ServiceCenter;
import productionmove.btl.entity.Warehouse;
import productionmove.btl.service.IAccountService;
import productionmove.btl.service.IAgencyService;
import productionmove.btl.service.IServiceCenterService;
import productionmove.btl.service.IWarehouseService;


@Controller
public class LoginController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IServiceCenterService iServiceCenterService;
    @GetMapping("/dang-nhap")
    public String homeLogin(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Account account = new Account();
        String mess = "";
        if(session.getAttribute("acc") == null){
            mess = "Vui lòng đăng nhập tài khoản!";
        } else {
            mess = "Bạn nhập sai tài khoản hoặc mật khẩu!";
        }


        model.addAttribute("mess", mess);
        model.addAttribute("account", account);
        return "login";
    }

    @PostMapping ("/login")
    public String login(Model model, Account account, HttpServletRequest request){
        Account acc = iAccountService.getAccByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(acc == null){
            HttpSession session = request.getSession();
            session.setAttribute("acc", account);
            session.setMaxInactiveInterval(60);
            return "redirect:/dang-nhap";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", acc);
            session.setMaxInactiveInterval(600);
            Agency agency = new Agency();
            Warehouse warehouse = new Warehouse();
            ServiceCenter serviceCenter = new ServiceCenter();
            for(Warehouse w: iWarehouseService.getAllWarehouse()){
                if(w.getName().equals(acc.getOffice())){
                    warehouse.setId(w.getId());
                }
            }
            for(Agency w: iAgencyService.getAllAgency()){
                if(w.getName().equals(acc.getOffice())){
                    agency.setId(w.getId());
                }
            }for(ServiceCenter w: iServiceCenterService.getAllServiceCenter()){
                if(w.getName().equals(acc.getOffice())){
                    serviceCenter.setId(w.getId());
                }
            }
            if(acc.getOffice().equals("Ban điều hành")){
                return "redirect:/bigcorp/bandieuhanh";
            } else if(agency.getId() != null) {
                return "redirect:/bigcorp/daily/" + agency.getId();
            } else if(warehouse.getId() != null) {
                return "redirect:/bigcorp/cssx/" + warehouse.getId();
            } else if(serviceCenter.getId() != null) {
                return "redirect:/bigcorp/trungtambaohanh/" + serviceCenter.getId();
            }
        }
        return null;
    }

    @GetMapping("/dang-xuat")
    public String logout(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/dang-nhap";
    }
}
