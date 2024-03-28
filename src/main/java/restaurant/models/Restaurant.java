package restaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize = 1)
    private long id;
    private String name;
    private String address;
    private String restType;
    private int numberOfEmployees;
    private int service;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<MenuItem> menuItemList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Restaurant(String name, String address, String restType, int numberOfEmployees, int service) {
        this.name = name;
        this.address = address;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }
}
