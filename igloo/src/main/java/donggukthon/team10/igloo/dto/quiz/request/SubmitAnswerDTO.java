package donggukthon.team10.igloo.dto.quiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SubmitAnswerDTO {
    private Long quizId;
    private String question;
    private String answer;
}
