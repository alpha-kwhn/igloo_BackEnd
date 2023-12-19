package donggukthon.team10.igloo.dto.paper.response;

import donggukthon.team10.igloo.domain.User;
import lombok.Builder;

@Builder
public record FindRollingPaperDTO(
    int design,
    Long paperId,
    PaperUserInfo writer
) { }
