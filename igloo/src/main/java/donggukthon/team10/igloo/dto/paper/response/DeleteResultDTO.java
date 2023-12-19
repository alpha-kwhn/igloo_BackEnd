package donggukthon.team10.igloo.dto.paper.response;

import lombok.Builder;

@Builder
public record DeleteResultDTO(
        Long paperId
) {}
