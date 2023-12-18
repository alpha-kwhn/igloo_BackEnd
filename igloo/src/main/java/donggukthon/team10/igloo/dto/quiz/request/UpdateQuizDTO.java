package donggukthon.team10.igloo.dto.quiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateQuizDTO {
    private Long quizId;
    private String question;
    private List<String> options;
    @JsonProperty("correct_answer")
    private String correctAnswer;
}
