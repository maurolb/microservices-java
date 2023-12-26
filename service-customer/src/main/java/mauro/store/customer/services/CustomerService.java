package mauro.store.customer.services;

import mauro.store.customer.entities.Customer;
import mauro.store.customer.entities.Region;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomerList();
    public List<Customer> getCustomerListByRegion(Region region);
    public Customer getCustomerById(Long id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);

}
