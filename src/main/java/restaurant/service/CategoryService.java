package restaurant.service;

import restaurant.dto.request.CategoryRequest;
import restaurant.dto.response.MassageResponse;

public interface CategoryService {
    MassageResponse save(CategoryRequest categoryRequest);
}
