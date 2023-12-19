package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IglooRepository extends JpaRepository<Igloo, Long> {
    Optional<Igloo> findByOwner(User user);
    boolean existsByCode(String code);
}
