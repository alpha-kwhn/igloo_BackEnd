package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
