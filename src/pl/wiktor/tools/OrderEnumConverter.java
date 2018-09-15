package pl.wiktor.tools;

import pl.wiktor.enums.OrderingEnum;

public class OrderEnumConverter {
    public OrderingEnum convertFromString(String input) {
        OrderingEnum returned_enum = null;
        switch (input) {
            case "asc":
                returned_enum = OrderingEnum.ASC;
                break;
            case "desc":
                returned_enum = OrderingEnum.DESC;
                break;
            default:
                returned_enum = OrderingEnum.ASC;
        }
        return returned_enum;
    }
}
