package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);
}
