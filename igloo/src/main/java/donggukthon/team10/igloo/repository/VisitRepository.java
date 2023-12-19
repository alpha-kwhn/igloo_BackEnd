package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
