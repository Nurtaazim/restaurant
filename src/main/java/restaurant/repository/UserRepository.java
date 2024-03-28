package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.exceptions.NotFoundException;
import restaurant.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userName);

    Optional<User> getByUserName(String userName);
    default User getUserByEmail(String email){
        return findByEmail(email).orElseThrow(()->new NotFoundException("User with this email not found!"));
    }
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
