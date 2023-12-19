package donggukthon.team10.igloo.dto.paper.response;

import lombok.Builder;

@Builder
public record OpenedPaper(
    boolean canRead,
    String contents
){}
