package restaurant.service;

import restaurant.dto.request.MenuItemRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.MenuItemResponse;
import restaurant.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    MassageResponse save(MenuItemRequest menuItemRequest);

    List<MenuItem> get(String name);

    MassageResponse setCategory(long foodId, long subCategoryId);

    List<MenuItemResponse> sortedByPrice();
}
