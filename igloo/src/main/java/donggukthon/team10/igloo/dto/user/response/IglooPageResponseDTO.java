package donggukthon.team10.igloo.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IglooPageResponseDTO {
    private String nickname;
    private String code;
    private String info;
    private boolean owner;
    private Long id;
}
