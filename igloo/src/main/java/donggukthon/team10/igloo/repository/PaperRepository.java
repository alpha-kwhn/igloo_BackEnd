package donggukthon.team10.igloo.repository;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.RollingPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaperRepository extends JpaRepository<RollingPaper, Long> {
    Optional<List<RollingPaper>> findAllRollingPapersByIgloo(Igloo igloo);

    @Query("SELECT rp FROM RollingPaper rp WHERE rp.writer.id = :userId AND rp.igloo.id = :iglooId")
    List<RollingPaper> findRollingPaperByWriterAndIgloo(@Param("iglooId") Long iglooId, @Param("userId") Long userId);

    @Query("SELECT rp FROM RollingPaper rp WHERE rp.igloo.id = :iglooId AND rp.id = :paperId")
    Optional<RollingPaper> findRollingPaperByIglooIdAndPaperId(@Param("iglooId") Long iglooId, @Param("paperId") Long paperId);

}
