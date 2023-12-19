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
    private String username;
    private String password;
    private String korName;
    private String nickname;
    private String role;
    @Builder
    public User(String username, String korName, String password, String nickname){
        this.username = username;
        this.password = password;
        this.korName = korName;
        this.nickname = nickname;
        this.role = "USER";
    }
}
