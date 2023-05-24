package productionmove.btl.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productionmove.btl.entity.Customer;
import productionmove.btl.repository.ICustomerRespository;
import productionmove.btl.service.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRespository iCustomerRespository;

    @Override
    public List<Customer> getAllCustomer() {
        return iCustomerRespository.findAll();
    }

    @Override
    public List<Customer> getAllCustomerByCodeagency(String code) {
        return iCustomerRespository.findAllByCodeagency(code);
    }

    @Override
    public Customer getCustomerById(Long id) throws Exception {
        return iCustomerRespository.findById(id).get();
    }

    @Override
    public Customer getCustomerByCode(String code) {
        return iCustomerRespository.findCustomerByCode(code);
    }

    @Override
    public Customer addCustomer(Customer acc) {
        return iCustomerRespository.save(acc);
    }

    @Override
    public Customer upadteCustomer(Customer acc) {
        return iCustomerRespository.save(acc);
    }

    @Override
    public void deleteCustomer(Long id) {
iCustomerRespository.deleteById(id);
    }
}
