package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByIglooOrderByScoreDesc(Igloo igloo);
}
