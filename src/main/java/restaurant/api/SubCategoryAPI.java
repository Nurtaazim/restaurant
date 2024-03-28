package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.SubCategoryRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.SubCategoryResponse;
import restaurant.models.Category;
import restaurant.models.SubCategory;
import restaurant.service.SubCategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subCategories")
public class SubCategoryAPI {
    private final SubCategoryService service;
    @PostMapping("/{categoryId}")
    MassageResponse save(@RequestBody SubCategoryRequest subCategoryRequest,
                         @PathVariable Long categoryId){
        return service.save(subCategoryRequest, categoryId);
    }
    @DeleteMapping("/{id}")
    MassageResponse delete(@PathVariable Long id){
        return service.delete(id);
    }
    @GetMapping
    Map<Category, List<SubCategory>> group(){
        return service.group();

    }
    @GetMapping("/{categoryId}")
    SubCategoryResponse sortByCategory(@PathVariable Long categoryId){
        return service.getAllByCategory(categoryId);
    }
}
