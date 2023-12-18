package donggukthon.team10.igloo.dto.quiz.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
public class ShowAllQuizzesDTO {
    private Long quizId;
    private String question;
    private List<String> options = new ArrayList<>();
    private String correctAnswer;
}
