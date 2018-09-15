package pl.wiktor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wiktor.dao.CustomerDAO;
import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public List<Customer> getSortedCustomers(CustomerEnum customerEnum, OrderingEnum orderEnum) {
        return customerDAO.getSortedCustomers(customerEnum, orderEnum);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }
}
