package restaurant.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import restaurant.dto.request.MenuItemRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.MenuItemResponse;
import restaurant.exceptions.NotFoundException;
import restaurant.models.MenuItem;
import restaurant.models.Restaurant;
import restaurant.models.SubCategory;
import restaurant.repository.MenuItemRepository;
import restaurant.repository.RestaurantRepository;
import restaurant.repository.SubCategoryRepository;
import restaurant.service.MenuItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public MassageResponse save(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setFoodType(menuItemRequest.getFoodType());
        Restaurant byId = restaurantRepository.getById();
        menuItem.setRestaurant(byId);
        menuItemRepository.save(menuItem);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully saved!").build();
    }

    @Override
    public List<MenuItem> get(String name) {
        return new ArrayList<>( menuItemRepository.find(name));
    }

    @Override
    @Transactional
    public MassageResponse setCategory(long foodId, long subCategoryId) {
        MenuItem menuItem = menuItemRepository.findById(foodId).orElseThrow(() -> new NotFoundException("Food with this id not found!"));
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new NotFoundException("Subcategory with this id not found!"));
        menuItem.setSubCategory(subCategory);
        subCategory.getMenuItems().add(menuItem);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Success!").build();
    }

    @Override
    public List<MenuItemResponse> sortedByPrice() {
        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
        List<MenuItem> menuItemList = menuItemRepository.sort();
        for (MenuItem menuItem : menuItemList) {
            MenuItemResponse menuItemResponse = new MenuItemResponse();
            menuItemResponse.setName(menuItem.getName());
            menuItemResponse.setImage(menuItem.getImage());
            menuItemResponse.setPrice(menuItem.getPrice());
            menuItemResponse.setDescription(menuItem.getDescription());
            menuItemResponse.setFoodType(menuItem.getFoodType());
            menuItemResponses.add(menuItemResponse);
        }
        return menuItemResponses;
    }
}
