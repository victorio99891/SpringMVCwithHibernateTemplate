package pl.wiktor.service;

import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    List<Customer> getSortedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum);

    List<Customer> getSortedPaginatedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum, int beginIndex, int lastIndex);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    void saveCustomer(Customer customer);

    Long getNumberOfCustomers();

    List<Customer> getCustomersInRange(int beginIndex, int lastIndex);

    List<Customer> getSearchResultCustomers(String constructedQuery);
}
