package donggukthon.team10.igloo.dto.quiz.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class SaveQuizDTO {
    private String question;
    private List<String> options;
    @JsonProperty("correct_answer")
    private String correctAnswer;

}
