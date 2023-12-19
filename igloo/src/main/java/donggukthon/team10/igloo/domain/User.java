package donggukthon.team10.igloo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String nickname;
    private String role;
    @Builder
    public User(Long id, String password, String nickname){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.role = "USER";
    }
}
