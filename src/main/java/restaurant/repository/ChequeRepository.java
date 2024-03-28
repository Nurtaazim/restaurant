package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.models.Cheque;
@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}
