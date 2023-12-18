package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Igloo {
    @Id
    @Column(name = "igloo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User owner;
    private String code;
    @Builder
    public Igloo(User owner, String code) {
        this.owner = owner;
        this.code = code;
    }
}
