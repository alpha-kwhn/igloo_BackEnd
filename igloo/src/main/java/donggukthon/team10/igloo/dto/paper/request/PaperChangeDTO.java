package donggukthon.team10.igloo.dto.paper.request;

import lombok.Builder;

@Builder
public record PaperChangeDTO (
        String content,
        int design
){}