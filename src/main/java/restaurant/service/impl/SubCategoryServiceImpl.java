package restaurant.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import restaurant.dto.request.SubCategoryRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.SubCategoryResponse;
import restaurant.exceptions.NotFoundException;
import restaurant.models.Category;
import restaurant.models.SubCategory;
import restaurant.repository.CategoryRepository;
import restaurant.repository.SubCategoryRepository;
import restaurant.service.SubCategoryService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public MassageResponse save(SubCategoryRequest subCategoryRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category with this id not found!"));
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.name());
        subCategoryRepository.save(subCategory);
        subCategory.setCategory(category);
        category.getSubCategories().add(subCategory);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Success!").build();
    }

    @Override
    public MassageResponse delete(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Subcategory with this id not found!"));
        subCategoryRepository.delete(subCategory);
        return MassageResponse.builder()
                .massage("Success!")
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Map<Category, List<SubCategory>> group() {
//        Map<Category, List<SubCategory>> categorySubCategoryMap = new HashMap<>();
//        List<Object[]> resultList = subCategoryRepository.resultList();
//        for (Object[] result : resultList) {
//            Category category = (Category) result[0];
//            SubCategory subCategory = (SubCategory) result[1];
//
//            categorySubCategoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(subCategory);
//        }
//
//        return categorySubCategoryMap;
        List<Category> allCategories = categoryRepository.findAll(); // Предполагается, что у вас есть репозиторий для работы с категориями
        List<SubCategory> allSubCategories = subCategoryRepository.findAll(); // Предполагается, что у вас есть репозиторий для работы с подкатегориями

        Map<Category, List<SubCategory>> categorySubCategoryMap = allCategories.stream()
                .collect(Collectors.toMap(
                        category -> category,
                        category -> allSubCategories.stream()
                                .filter(subCategory -> subCategory.getCategory().equals(category))
                                .collect(Collectors.toList())
                ));
        System.out.println(categorySubCategoryMap);
        return categorySubCategoryMap;
    }

    @Override
    public SubCategoryResponse getAllByCategory(Long categoryId) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        for (SubCategory subCategory : subCategoryRepository.findAll()) {
            if (subCategory.getCategory().getId()==categoryId){
                subCategoryResponse.getList().add(subCategory);
            }
        }
        return subCategoryResponse;
    }


}
