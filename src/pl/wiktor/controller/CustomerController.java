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
import pl.wiktor.tools.CustomerSearch;
import pl.wiktor.tools.CustomerSearchImpl;
import pl.wiktor.tools.OrderEnumConverter;

import java.util.ArrayList;
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

    /*@GetMapping("/pagination")
    public String paginationCustomers(Model theModel, @RequestParam("pageNumber") String currentPage, @RequestParam("showOnPage") String numberOnPage) {
        int numberOfCustomers = customerService.getNumberOfCustomers().intValue();
        int lastPageNumber = (int) (Math.ceil((double) numberOfCustomers / Integer.parseInt(numberOnPage)));
        List<Customer> customerList = customerService.getCustomersInRange(((Integer.parseInt(currentPage) - 1) * Integer.parseInt(numberOnPage)), Integer.parseInt(numberOnPage));
        theModel.addAttribute("showOnPage", Integer.parseInt(numberOnPage));
        theModel.addAttribute("customers", customerList);
        theModel.addAttribute("lastPage", lastPageNumber);
        theModel.addAttribute("currentPage", Integer.parseInt(currentPage));
        return "pagination-customers";
    }*/

    @GetMapping("/pagination")
    public String paginationOrderedCustomers(Model theModel, @RequestParam("orderBy") String customerEnum, @RequestParam("direction") String orderingEnum, @RequestParam("pageNumber") String currentPage, @RequestParam("showOnPage") String numberOnPage, @ModelAttribute("searchBar") CustomerSearchImpl customerSearch) {
        int numberOfCustomers = customerService.getNumberOfCustomers().intValue();
        int lastPageNumber = (int) (Math.ceil((double) numberOfCustomers / Integer.parseInt(numberOnPage)));

        List<Customer> customerList = customerService.getSortedPaginatedCustomers(
                new CustomerEnumConverter().convertFromString(customerEnum),
                new OrderEnumConverter().convertFromString(orderingEnum),
                ((Integer.parseInt(currentPage) - 1) * Integer.parseInt(numberOnPage)),
                Integer.parseInt(numberOnPage));

        theModel.addAttribute("customersNumber", numberOfCustomers);
        theModel.addAttribute("showOnPage", Integer.parseInt(numberOnPage));
        theModel.addAttribute("customers", customerList);
        theModel.addAttribute("lastPage", lastPageNumber);
        theModel.addAttribute("currentPage", Integer.parseInt(currentPage));
        theModel.addAttribute("orderBy", customerEnum);
        theModel.addAttribute("direction", orderingEnum);
        return "pagination-customers";
    }

    @PostMapping("/searchCustomer")
    public String searchForCustomer(Model theModel, @ModelAttribute("searchBar") CustomerSearchImpl customerSearch) {

        customerSearch.setSearchWord(customerSearch.getSearchWord().replaceAll("\\s+", ""));

        String constructedQuery = "from Customer u where u." + customerSearch.getSearchBy() + " like '" + customerSearch.getSearchWord() + "%' or u." + customerSearch.getSearchBy() + " like '%" + customerSearch.getSearchWord() + "%' or u." + customerSearch.getSearchBy() + " like '%" + customerSearch.getSearchWord() + "'";


        List<Customer> customerList = customerService.getSearchResultCustomers(constructedQuery);


        theModel.addAttribute("customers", customerList);


        return "search-pagination-customers";
    }

    /*@GetMapping("/pagination/{customerEnum}/{orderEnum}")
    public String listSortedPaginatedCustomers(Model theModel, @PathVariable("customerEnum") String customerEnum, @PathVariable("orderEnum") String orderingEnum, @RequestParam("pageNumber") String currentPage, @RequestParam("showOnPage") String numberOnPage) {

        int numberOfCustomers = customerService.getNumberOfCustomers().intValue();
        int lastPageNumber = (int) (Math.ceil((double) numberOfCustomers / Integer.parseInt(numberOnPage)));

        List<Customer> customerList = customerService.getSortedPaginatedCustomers(
                new CustomerEnumConverter().convertFromString(customerEnum),
                new OrderEnumConverter().convertFromString(orderingEnum),
                ((Integer.parseInt(currentPage) - 1) * Integer.parseInt(numberOnPage)),
                Integer.parseInt(numberOnPage));

        theModel.addAttribute("showOnPage", Integer.parseInt(numberOnPage));
        theModel.addAttribute("customers", customerList);
        theModel.addAttribute("lastPage", lastPageNumber);
        theModel.addAttribute("currentPage", Integer.parseInt(currentPage));


        return "pagination-customers";
    }*/

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
