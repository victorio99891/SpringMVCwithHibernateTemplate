package pl.wiktor.controller;


import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.dao.CustomerDAO;
import pl.wiktor.dao.CustomerDAOImpl;
import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;
import pl.wiktor.service.CustomerService;
import pl.wiktor.service.CustomerServiceImpl;
import pl.wiktor.tools.CustomerEnumConverter;
import pl.wiktor.tools.OrderEnumConverter;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Customer> theCustomers = customerService.getCustomers();
        // List<Customer> theCustomers = customerService.getSortedCustomers(CustomerEnum.FIRST_NAME, OrderingEnum.ASC);
        for (Customer customer : theCustomers) {
            System.out.println(customer.toString());
        }
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }


    @GetMapping("/list/{customerEnum}/{orderEnum}")
    public String listSortedCustomers(Model theModel, @PathVariable("customerEnum") String customerEnum, @PathVariable("orderEnum") String orderingEnum) {
        List<Customer> theCustomers = customerService.getSortedCustomers(
                new CustomerEnumConverter().convertFromString(customerEnum),
                new OrderEnumConverter().convertFromString(orderingEnum));
        for (Customer customer : theCustomers) {
            System.out.println(customer.toString());
        }
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormAdd")
    public String showCustomerForm(@ModelAttribute("customer") Customer customer) {
        return "customer-form";
    }


    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model theModel) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

}
