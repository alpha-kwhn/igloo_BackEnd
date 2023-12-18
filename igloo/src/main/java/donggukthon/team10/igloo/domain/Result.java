package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "igloo_id")
    private Igloo igloo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User solver;
    private int score;
    @Builder
    public Result(Igloo igloo, User solver, int score) {
        this.igloo = igloo;
        this.solver = solver;
        this.score = score;
    }
}
