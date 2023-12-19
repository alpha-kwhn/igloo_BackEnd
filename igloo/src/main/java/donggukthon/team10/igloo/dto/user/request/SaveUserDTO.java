package donggukthon.team10.igloo.dto.user.request;

import lombok.Getter;

@Getter
public class SaveUserDTO {
    private String id;
    private String password;
    private String korName;
    private String nickname;
}
