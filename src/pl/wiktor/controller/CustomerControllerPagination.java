package pl.wiktor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.entity.Customer;
import pl.wiktor.service.CustomerService;
import pl.wiktor.tools.CustomerEnumConverter;
import pl.wiktor.tools.CustomerSearchImpl;
import pl.wiktor.tools.OrderEnumConverter;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerControllerPagination {


    @Autowired
    CustomerService customerService;

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
        //MYSQL: String constructedQuery = "from Customer u where u." + customerSearch.getSearchBy() + " like '" + customerSearch.getSearchWord() + "%' or u." + customerSearch.getSearchBy() + " like '%" + customerSearch.getSearchWord() + "%' or u." + customerSearch.getSearchBy() + " like '%" + customerSearch.getSearchWord() + "'";
        //H2:
        String constructedQuery = "from Customer u where lower(u." + customerSearch.getSearchBy() + ") like lower('" + customerSearch.getSearchWord() + "%') or lower(u." + customerSearch.getSearchBy() + ") like lower('%" + customerSearch.getSearchWord() + "%') or lower(u." + customerSearch.getSearchBy() + ") like lower('%" + customerSearch.getSearchWord() + "')";
        List<Customer> customerList = customerService.getSearchResultCustomers(constructedQuery);
        theModel.addAttribute("customers", customerList);
        return "search-customers";
    }


}
