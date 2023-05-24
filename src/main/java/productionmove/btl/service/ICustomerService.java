package productionmove.btl.service;

import productionmove.btl.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> getAllCustomer();
    public List<Customer> getAllCustomerByCodeagency(String code);
    public Customer getCustomerById(Long id) throws Exception;
    public Customer getCustomerByCode(String code);
    public Customer addCustomer(Customer acc);
    public Customer upadteCustomer(Customer acc);
    public void deleteCustomer(Long id);
}
