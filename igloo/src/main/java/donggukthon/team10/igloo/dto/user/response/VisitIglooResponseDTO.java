package donggukthon.team10.igloo.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VisitIglooResponseDTO {
    private Long iglooId;
    private String nickname;
    private String code;
}
