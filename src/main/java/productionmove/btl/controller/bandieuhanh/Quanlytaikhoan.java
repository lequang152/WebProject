package productionmove.btl.controller.bandieuhanh;

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

import java.util.ArrayList;
import java.util.List;

@Controller
public class Quanlytaikhoan {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IWarehouseService iWarehouseService;
    @Autowired
    private IAgencyService iAgencyService;
    @Autowired
    private IServiceCenterService iServiceCenterService;

    @GetMapping("/bigcorp/bandieuhanh/quanly-taikhoan")
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

        List<Account> accountList = iAccountService.getAllAcc();
        model.addAttribute("listAcc", accountList);
        return "quanlytaikhoan";
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-taikhoan/themtaikhoan/{status}")
    public String addAcc(Model model, HttpServletRequest request, @PathVariable String status){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);

        List<Warehouse> warehouses = iWarehouseService.getAllWarehouse();
        List<Agency> agencys = iAgencyService.getAllAgency();
        List<ServiceCenter> serviceCenters = iServiceCenterService.getAllServiceCenter();
        List<String> offices = new ArrayList<>();
        offices.add("Ban điều hành");
        for(Warehouse w: warehouses){
            offices.add(w.getName());
        }
        for(Agency w: agencys){
            offices.add(w.getName());
        }
        for(ServiceCenter w: serviceCenters){
            offices.add(w.getName());
        }
        if(Integer.valueOf(status) == 1){
            model.addAttribute("mess", "");
        } else if(Integer.valueOf(status) == 0){
            model.addAttribute("mess", "Tài khoản đã tồn tại!");
        } else if(Integer.valueOf(status) == 2){
            model.addAttribute("mess", "Vui lòng nhập tài khoản từ 6 ký tự và mật khẩu từ 8 ký tự!");
        }
        model.addAttribute("listOffice",offices);

        model.addAttribute("account", new Account());
        return "themtaikhoan";
    }

    @PostMapping("/bigcorp/bandieuhanh/quanly-taikhoan/themtaikhoan")
    public String save(Model model, Account account, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        if(account.getUsername().equals("") || account.getPassword().equals("") || account.getUsername().length() < 6 || account.getPassword().length() < 8){
            return "redirect:/bigcorp/bandieuhanh/quanly-taikhoan/themtaikhoan/2";
        } else {
            List<Account> list = iAccountService.getAllAcc();
            boolean check = true;
            for(Account a: list){
                if(a.getUsername().equals(account.getUsername())){
                    check = false;
                }
            }
            if(!check){
                return "redirect:/bigcorp/bandieuhanh/quanly-taikhoan/themtaikhoan/0";
            } else {
                iAccountService.addAcc(account);
                return "redirect:/bigcorp/bandieuhanh/quanly-taikhoan";
            }
        }
    }

    @GetMapping("/bigcorp/bandieuhanh/quanly-taikhoan/xoataikhoan/{id}")
    public String delete(Model model, HttpServletRequest request, @PathVariable String id){
        HttpSession session = request.getSession();
        if(session.getAttribute("acc") == null) {
            return "redirect:/dang-nhap";
        }
        session.setMaxInactiveInterval(600);
        iAccountService.deleteAcc(Long.parseLong(id));
        return "redirect:/bigcorp/bandieuhanh/quanly-taikhoan";
    }
}
