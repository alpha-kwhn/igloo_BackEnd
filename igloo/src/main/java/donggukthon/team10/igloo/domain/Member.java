package donggukthon.team10.igloo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "igloo_id")
    private Igloo igloo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
