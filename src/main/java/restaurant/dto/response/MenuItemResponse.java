package restaurant.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import restaurant.enums.FoodType;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemResponse {
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
}
