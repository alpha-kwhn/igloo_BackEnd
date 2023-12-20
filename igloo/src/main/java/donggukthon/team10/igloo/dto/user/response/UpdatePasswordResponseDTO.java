package donggukthon.team10.igloo.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePasswordResponseDTO {
    private String newPassword;
}
