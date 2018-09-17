package pl.wiktor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.entity.Customer;
import pl.wiktor.service.CustomerService;
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
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/list/{customerEnum}/{orderEnum}")
    public String listSortedCustomers(Model theModel, @PathVariable("customerEnum") String customerEnum, @PathVariable("orderEnum") String orderingEnum) {
        List<Customer> theCustomers = customerService.getSortedCustomers(
                new CustomerEnumConverter().convertFromString(customerEnum),
                new OrderEnumConverter().convertFromString(orderingEnum));
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showFormAdd")
    public String showCustomerForm(@ModelAttribute("customer") Customer customer) {
        return "customer-form";
    }

    @GetMapping("/showFormUpdate")
    public String showCustomerUpdateForm(Model theModel, @RequestParam("customerId") int id) {
        Customer customer = customerService.getCustomer(id);
        theModel.addAttribute("customer", customer);
        return "customer-form";
    }


    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model theModel) {
        customerService.saveCustomer(customer);
        theModel.addAttribute("pageNumber", 1);
        theModel.addAttribute("showOnPage", 5);
        theModel.addAttribute("orderBy", "last_name");
        theModel.addAttribute("direction", "asc");
        return "redirect:/customer/pagination";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int id, Model theModel) {
        customerService.deleteCustomer(id);
        theModel.addAttribute("pageNumber", 1);
        theModel.addAttribute("showOnPage", 5);
        theModel.addAttribute("orderBy", "last_name");
        theModel.addAttribute("direction", "asc");
        return "redirect:/customer/pagination";
    }

}
