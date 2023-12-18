package donggukthon.team10.igloo.dto.quiz.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankingDTO {
    private int rank;
    private String nickname;
    private int score;
    private boolean owner;
}
