package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Quiz;
import donggukthon.team10.igloo.dto.quiz.request.SaveQuizDTO;
import donggukthon.team10.igloo.dto.quiz.response.ShowAllQuizzesDTO;
import donggukthon.team10.igloo.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final IglooService iglooService;
    @Transactional
    public void saveQuizzes(Long iglooId, List<SaveQuizDTO> quizzes){
        iglooService.generateIgloo(); //-> 테스트용 코드
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
    public List<ShowAllQuizzesDTO> showAllQuizzes(Long iglooId){
        return quizRepository.findAllByIgloo(iglooService.findById(iglooId)).stream()
                .map(quiz -> {
                    return ShowAllQuizzesDTO.builder()
                            .quizId(quiz.getId())
                            .question(quiz.getQuestion())
                            .options(List.of(quiz.getOptionFirst(), quiz.getOptionSecond(), quiz.getOptionThird(), quiz.getOptionFourth()))
                            .correctAnswer(quiz.getAnswer())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
