package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
