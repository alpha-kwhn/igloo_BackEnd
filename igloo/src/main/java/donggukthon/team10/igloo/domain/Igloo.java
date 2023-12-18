package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
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
}
