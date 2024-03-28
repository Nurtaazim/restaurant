package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.models.Restaurant;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    default Restaurant getById(){
        return findById(1L).orElse(null);
    }
    @Query("select count(u) from User u where u.restaurant.id=1 ")
    int getUsersSize();
}
