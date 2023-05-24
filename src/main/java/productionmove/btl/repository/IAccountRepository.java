package productionmove.btl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Account;


import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Account findAccountById(Long id);
    Account findAccountByUsernameAndPassword(String username, String password);
}
