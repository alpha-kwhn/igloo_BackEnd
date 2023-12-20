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
    private String info;
    private String role;
    @Builder
    public User(String username, String korName, String password, String nickname){
        this.username = username;
        this.password = password;
        this.korName = korName;
        this.nickname = nickname;
        this.info = nickname + "의 이글루에 오신 걸 환영합니다~!";
        this.role = "USER";
    }
    public String updateNickname(String nickname){
        this.nickname = nickname;
        return this.nickname;
    }
    public String updateInfo(String info){
        this.info = info;
        return this.info;
    }
    public void updatePassword(String password){
        this.password = password;
    }
}
