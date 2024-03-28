package restaurant.service;

import restaurant.dto.request.SubCategoryRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.SubCategoryResponse;
import restaurant.models.Category;
import restaurant.models.SubCategory;

import java.util.List;
import java.util.Map;

public interface SubCategoryService {
    MassageResponse save(SubCategoryRequest subCategoryRequest, Long categoryId);

    MassageResponse delete(Long id);
    Map<Category, List<SubCategory>> group();

    SubCategoryResponse getAllByCategory(Long categoryId);
}
