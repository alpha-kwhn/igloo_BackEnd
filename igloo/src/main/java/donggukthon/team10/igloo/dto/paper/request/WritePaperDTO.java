package donggukthon.team10.igloo.dto.paper.request;

에import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record WritePaperDTO(
        Long userId,
        String contents,
        int design
) {}
