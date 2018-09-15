package pl.wiktor.dao;

import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    List<Customer> getSortedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum);

    Customer getCustomer(int id);

    void saveCustomer(Customer customer);

    void deleteUser(int id);
}
