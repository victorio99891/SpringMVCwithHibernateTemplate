package pl.wiktor.tools;

import pl.wiktor.enums.CustomerEnum;

public class CustomerEnumConverter {


    public CustomerEnum convertFromString(String input) {
        CustomerEnum customerEnum = null;
        switch (input) {
            case "first_name":
                customerEnum = CustomerEnum.FIRST_NAME;
                break;
            case "last_name":
                customerEnum = CustomerEnum.LAST_NAME;
                break;
            case "email":
                customerEnum = CustomerEnum.EMAIL;
                break;
        }
        return customerEnum;
    }


}
