package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.dto.request.CategoryRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryAPI {
    private final CategoryService categoryService;
    @PostMapping
    MassageResponse create(@RequestBody CategoryRequest categoryRequest){
       return categoryService.save(categoryRequest);
    }
}
