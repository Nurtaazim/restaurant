package restaurant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import restaurant.dto.request.CategoryRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.models.Category;
import restaurant.repository.CategoryRepository;
import restaurant.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public MassageResponse save(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return MassageResponse.builder().massage("Success!").httpStatus(HttpStatus.OK).build();
    }
}
