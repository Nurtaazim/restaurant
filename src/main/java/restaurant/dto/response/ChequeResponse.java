package restaurant.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ChequeResponse {
    private String waitersName;
    private List<MenuItemResponse> menuItemResponseList;
    private int price;
    private int service;
    private int fullPrice;
}
