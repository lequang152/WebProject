package productionmove.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import productionmove.btl.entity.Account;
import productionmove.btl.entity.Customer;

import java.util.List;

public interface ICustomerRespository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    Customer findCustomerById(Long id);
    Customer findCustomerByCode(String code);
    List<Customer> findAllByCodeagency(String code);
}
