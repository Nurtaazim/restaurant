package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.models.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select m from MenuItem m where LOWER(m.name) like LOWER(concat('%', :name, '%'))")
    List<MenuItem> find(String name);
    @Query("select m from MenuItem m order by m.price")
    List<MenuItem> sort();
}
