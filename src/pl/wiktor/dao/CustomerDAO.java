package pl.wiktor.dao;

import pl.wiktor.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

}
