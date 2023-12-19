package donggukthon.team10.igloo.dto.paper.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record WritePaperDTO(
        Long userId,
        String contents,
        int design
) {}
