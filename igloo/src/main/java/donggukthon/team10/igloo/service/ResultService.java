package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Result;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    @Transactional
    public void saveResult(Igloo igloo, User user, int score){
        resultRepository.save(Result.builder()
                        .igloo(igloo)
                        .solver(user)
                        .score(score)
                .build());
    }

}
