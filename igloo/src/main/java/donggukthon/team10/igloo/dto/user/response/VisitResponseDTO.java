package donggukthon.team10.igloo.dto.user.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VisitResponseDTO {
    private List<VisitIglooResponseDTO> iVisit;
    private List<VisitIglooResponseDTO> whoVisited;
}
