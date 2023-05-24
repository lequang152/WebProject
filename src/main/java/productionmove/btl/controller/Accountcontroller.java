package productionmove.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import productionmove.btl.entity.Account;
import productionmove.btl.service.IAccountService;


import java.util.List;
import java.util.Map;

@Controller
public class Accountcontroller {
    @Autowired
    private IAccountService iAccountService;

    @GetMapping("/acc")
    public ResponseEntity<List<Account>> getAllAcc(){
        List<Account> accountList = iAccountService.getAllAcc();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping("/acc")
    public ResponseEntity<Map<String, ?>> addAcc(@RequestBody Account account){
        try {
            Account newAcc = iAccountService.addAcc(account);
            return ResponseEntity.ok().body(Map.of("account", newAcc));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
}
