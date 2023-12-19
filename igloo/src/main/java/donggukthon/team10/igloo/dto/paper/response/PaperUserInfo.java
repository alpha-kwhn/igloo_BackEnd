package donggukthon.team10.igloo.dto.paper.response;

import lombok.Builder;

@Builder
public record PaperUserInfo(
        Long userId,
        String korName,
        String nickname
) {}
