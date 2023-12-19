package donggukthon.team10.igloo.dto.auth.response;

import lombok.Builder;

@Builder
public record LoginResponse (
        Long id,
        String nickName,
        String accessToken
) {}