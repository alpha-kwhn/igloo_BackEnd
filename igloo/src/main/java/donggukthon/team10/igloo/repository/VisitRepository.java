package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT DISTINCT v FROM Visit v WHERE v.visitor = :visitor ORDER BY v.visitedTime DESC")
    List<Visit> findDistinctRecentVisitsByVisitor(@Param("visitor") Igloo visitor);
    @Query("SELECT DISTINCT v FROM Visit v WHERE v.visited = :visited ORDER BY v.visitedTime DESC")
    List<Visit> findDistinctRecentVisitsByVisited(@Param("visited") Igloo visited);


}
