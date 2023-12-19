package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Builder
    public RollingPaper(Igloo igloo, User writer, String content, int design) {
        this.igloo = igloo;
        this.writer = writer;
        this.content = content;
        this.design = design;
    }
}
