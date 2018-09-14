package pl.wiktor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wiktor.dao.CustomerDAO;
import pl.wiktor.dao.CustomerDAOImpl;
import pl.wiktor.entity.Customer;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomers(Model theModel) {

        List<Customer> theCustomers = customerDAO.getCustomers();

        for (Customer customer : theCustomers) {
            System.out.println(customer.toString());
        }

        theModel.addAttribute("customers", theCustomers);


        return "list-customers";
    }

}
