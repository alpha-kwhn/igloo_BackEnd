package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.dto.quiz.request.SaveQuizDTO;
import donggukthon.team10.igloo.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @PostMapping("/{iglooId}")
    public ApiResponse saveQuizzes(@PathVariable Long iglooId, @RequestBody List<SaveQuizDTO> quizzes){
        quizService.saveQuizzes(iglooId, quizzes);
        return ApiResponse.nullDataBuilder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }
    @GetMapping("/{iglooId}")
    public ApiResponse showQuizzes(@PathVariable Long iglooId){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(quizService.showAllQuizzes(iglooId))
                .build();
    }
}
