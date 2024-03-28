package restaurant.dto.response;

import lombok.Getter;
import lombok.Setter;
import restaurant.models.SubCategory;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class SubCategoryResponse {
    private List<SubCategory> list = new ArrayList<>();
}
