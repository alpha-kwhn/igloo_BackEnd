package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IglooRepository extends JpaRepository<Igloo, Long> {
}
