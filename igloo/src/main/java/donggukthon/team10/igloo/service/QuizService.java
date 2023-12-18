package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Quiz;
import donggukthon.team10.igloo.dto.quiz.request.SaveQuizDTO;
import donggukthon.team10.igloo.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final IglooService iglooService;
    @Transactional
    public void saveQuizzes(Long iglooId, List<SaveQuizDTO> quizzes){
        //iglooService.generateIgloo(); //-> 테스트용 코드
        //TODO: 이글루 생성 코드도 완성하고 확인 다시 필요

        Igloo findIgloo = iglooService.findById(iglooId);
        quizzes.stream()
                .forEach(quiz -> {
                    log.info(quiz.getCorrectAnswer());
                    quizRepository.save(Quiz.builder()
                                    .igloo(findIgloo)
                                    .question(quiz.getQuestion())
                                    .answer(quiz.getCorrectAnswer())
                                    .optionFirst(quiz.getOptions().get(0))
                                    .optionSecond(quiz.getOptions().get(1))
                                    .optionThird(quiz.getOptions().get(2))
                                    .optionFourth(quiz.getOptions().get(3))
                            .build());
                }
                );
    }
}
