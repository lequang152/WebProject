package productionmove.btl.service;

import productionmove.btl.entity.Account;


import java.util.List;

public interface IAccountService {
    public List<Account> getAllAcc();
    public Account getAccById(Long id) throws Exception;
    public Account getAccByUsernameAndPassword(String username, String password);
    public Account addAcc(Account acc);
    public Account upadteAcc(Account acc);
    public void deleteAcc(Long id);
}
