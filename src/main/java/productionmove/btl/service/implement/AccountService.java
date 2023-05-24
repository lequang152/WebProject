package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Account;
import productionmove.btl.repository.IAccountRepository;
import productionmove.btl.service.IAccountService;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Override
    public List<Account> getAllAcc() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account getAccById(Long id) throws Exception {
        Optional<Account> accountOptional = iAccountRepository.findById(id);
        if(!accountOptional.isPresent()){
            throw new Exception("Could not find account with id: " + id);
        }
        return accountOptional.get();
    }

    @Override
    public Account getAccByUsernameAndPassword(String username, String password) {
        Account acc = iAccountRepository.findAccountByUsernameAndPassword(username, password);
        return acc;
    }

    @Override
    public Account addAcc(Account acc) {
        return iAccountRepository.save(acc);
    }

    @Override
    public Account upadteAcc(Account acc) {
        return iAccountRepository.save(acc);
    }

    @Override
    public void deleteAcc(Long id) {
        iAccountRepository.deleteById(id);
    }


}
