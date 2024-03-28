package restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subCategories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen", sequenceName = "subCategory_seq", allocationSize = 1)
    private long id;
    private String name;
    @OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}
