package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.RollingPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<RollingPaper, Long> {
}
