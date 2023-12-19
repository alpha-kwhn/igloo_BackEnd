package donggukthon.team10.igloo.dto.paper.response;

import donggukthon.team10.igloo.domain.RollingPaper;
import donggukthon.team10.igloo.domain.User;
import lombok.Builder;

@Builder
public record CreatedRollingPaperDTO (
        Long paperId,
        PaperUserInfo creator,
        int design
) {}