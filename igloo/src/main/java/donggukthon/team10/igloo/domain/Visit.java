package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Visit {
    @Id
    @Column(name = "visit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "visitor_igloo_id") // visitor 엔티티에 대한 별칭 추가
    private Igloo visitor;

    @ManyToOne
    @JoinColumn(name = "visited_igloo_id") // visited 엔티티에 대한 별칭 추가
    private Igloo visited;
    private LocalDateTime visitedTime;
    @Builder
    public Visit(Igloo visitor, Igloo visited, LocalDateTime visitedTime) {
        this.visitor = visitor;
        this.visited = visited;
        this.visitedTime = visitedTime;
    }
}
