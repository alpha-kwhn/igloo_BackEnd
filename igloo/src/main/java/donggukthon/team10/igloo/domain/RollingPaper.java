package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RollingPaper {
    @Id
    @Column(name = "paper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "igloo_id")
    private Igloo igloo;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User writer;
    private String content;
    private int design;
}
