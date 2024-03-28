package restaurant.models;

import jakarta.persistence.*;
import lombok.*;
import restaurant.enums.FoodType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menuItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuItem_gen")
    @SequenceGenerator(name = "menuItem_gen", sequenceName = "menuItem_seq", allocationSize = 1)
    private long id;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    @OneToOne(mappedBy = "menuItem")
    private StopList stopList;
    @ManyToOne
    private SubCategory subCategory;
    @ManyToOne
    private Restaurant restaurant;

}
