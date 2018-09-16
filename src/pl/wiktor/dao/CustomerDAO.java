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

    Long getNumberOfCustomers();

    List<Customer> getCustomersInRange(int beginIndex, int lastIndex);

    List<Customer> getSortedPaginatedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum, int beginIndex, int lastIndex);

    List<Customer> getSearchResultCustomers(String constructedQuery);
}
