package donggukthon.team10.igloo.dto.auth.response;

import donggukthon.team10.igloo.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDTO {
    private User user;
    private String token;
    private String code;
}
