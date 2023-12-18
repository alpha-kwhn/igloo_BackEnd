package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Quiz;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByIgloo(Igloo igloo);
}
