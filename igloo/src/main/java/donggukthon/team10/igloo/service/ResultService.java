package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Result;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.quiz.response.RankingDTO;
import donggukthon.team10.igloo.dto.quiz.response.ShowRankingDTO;
import donggukthon.team10.igloo.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final IglooService iglooService;
    private final UserService userService;
    @Transactional
    public void saveResult(Igloo igloo, User user, int score){
        resultRepository.save(Result.builder()
                        .igloo(igloo)
                        .solver(user)
                        .score(score)
                .build());
    }
    public ShowRankingDTO showRanking(Long iglooId){
        Igloo findIgloo = iglooService.findById(iglooId);
        AtomicInteger rank = new AtomicInteger(1);
        List<RankingDTO> standing = resultRepository.findAllByIglooOrderByScoreDesc(findIgloo).stream()
                .map(result -> RankingDTO.builder()
                        .rank(rank.getAndIncrement())
                        .nickname(result.getSolver().getNickname())
                        .score(result.getScore())
                        .build()
                ).collect(Collectors.toList());

        log.info("통과?");
        return ShowRankingDTO.builder()
                .standing(standing)
                .owner(findIgloo.getOwner().getId().equals(userService.getLoginUser().getId()))
                .build();
    }
}
