package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.MenuItemRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.MenuItemResponse;
import restaurant.models.MenuItem;
import restaurant.service.MenuItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menuItem")
public class MenuAPI {
    private final MenuItemService service;

    @PostMapping()
    MassageResponse save (@RequestBody MenuItemRequest menuItemRequest){
        return service.save(menuItemRequest);
    }
    @GetMapping
    List<MenuItem> find (@RequestParam String name){
        return service.get(name);
    }
    @PutMapping("/{foodId}/{subCategoryId}")
    MassageResponse setSubCategory(@PathVariable long foodId,
                                   @PathVariable long subCategoryId){
        return service.setCategory(foodId, subCategoryId);
    }
    @GetMapping("/sort")
    List<MenuItemResponse> sort(){
        return service.sortedByPrice();
    }

}
