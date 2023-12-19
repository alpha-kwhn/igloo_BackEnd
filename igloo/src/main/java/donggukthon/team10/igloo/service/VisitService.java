package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Visit;
import donggukthon.team10.igloo.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    @Transactional
    public void saveRecord(Igloo visitor, Igloo visited){
        visitRepository.save(
                Visit.builder()
                        .visitor(visitor)
                        .visited(visited)
                        .visitedTime(LocalDateTime.now())
                .build()
        );
    }
}
