package pl.wiktor.service;

import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    List<Customer> getSortedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum);
    void saveCustomer(Customer customer);
}
