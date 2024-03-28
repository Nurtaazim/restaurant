package restaurant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import restaurant.dto.response.ChequeResponse;
import restaurant.dto.response.MenuItemResponse;
import restaurant.models.Cheque;
import restaurant.models.MenuItem;
import restaurant.models.User;
import restaurant.repository.ChequeRepository;
import restaurant.repository.MenuItemRepository;
import restaurant.repository.UserRepository;
import restaurant.service.ChequeService;
import restaurant.service.MenuItemService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public ChequeResponse create(List<Integer> menuItemsId) {
        Cheque cheque = new Cheque();
        List<MenuItem> menuItemList = new ArrayList<>();
        ChequeResponse chequeResponse = new ChequeResponse();
        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.getUserByEmail(userName);
        chequeResponse.setWaitersName(null);
        List<MenuItem> all = menuItemRepository.findAll();
        for (MenuItem menuItem : all) {
            for (Integer i : menuItemsId) {
                if (menuItem.getId() == i){
                    menuItemList.add(menuItem);
                    MenuItemResponse menuItemResponse = new MenuItemResponse();
                    menuItemResponse.setName(menuItem.getName());
                    menuItemResponse.setDescription(menuItem.getDescription());
                    menuItemResponse.setImage(menuItem.getImage());
                    menuItemResponse.setPrice(menuItem.getPrice());
                    menuItemResponse.setFoodType(menuItem.getFoodType());
                    menuItemResponses.add(menuItemResponse);
                }
            }
        }
        chequeResponse.setMenuItemResponseList(menuItemResponses);
        int price = 0;
        for (MenuItemResponse menuItemRespons : menuItemResponses) {
            price = price + menuItemRespons.getPrice().intValue();
        }
        chequeResponse.setPrice(price);
        int service = price/100*11;
        chequeResponse.setService(service);
        int full = service + price;
        chequeResponse.setFullPrice(full);
        cheque.setMenuItemList(menuItemList);
        cheque.setUser(null);
        cheque.setPrice(BigDecimal.valueOf(full));
        chequeRepository.save(cheque);
        return chequeResponse ;
    }
}
