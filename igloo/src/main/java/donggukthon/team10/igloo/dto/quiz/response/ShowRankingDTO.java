package donggukthon.team10.igloo.dto.quiz.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class ShowRankingDTO {
    private List<RankingDTO> standing;
    private boolean owner;
}
