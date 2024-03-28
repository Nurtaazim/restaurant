package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.models.StopList;
@Repository
public interface StopListRepository extends JpaRepository<StopList, Long> {
}
