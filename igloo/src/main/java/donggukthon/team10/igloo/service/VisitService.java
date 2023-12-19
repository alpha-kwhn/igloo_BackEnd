package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.Igloo;
import donggukthon.team10.igloo.domain.Visit;
import donggukthon.team10.igloo.dto.user.response.VisitIglooResponseDTO;
import donggukthon.team10.igloo.dto.user.response.VisitResponseDTO;
import donggukthon.team10.igloo.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public VisitResponseDTO showVisits(Igloo myIgloo){
        return VisitResponseDTO.builder()
                .iVisit(showMyVisitRecords(myIgloo))
                .whoVisited(showMyVisitedRecords(myIgloo))
                .build();
    }
    // 내가 방문한
    public List<VisitIglooResponseDTO> showMyVisitRecords(Igloo myIgloo) {
        List<Visit> distinctRecentVisits = visitRepository.findDistinctRecentVisitsByVisitor(myIgloo);
        Set<Igloo> uniqueVisited = getUniqueVisited(distinctRecentVisits);

        List<Igloo> sortedUniqueVisited = uniqueVisited.stream()
                .sorted(Comparator.comparing(igloo -> {
                    Optional<Visit> latestVisit = distinctRecentVisits.stream()
                            .filter(visit -> visit.getVisited().equals(igloo))
                            .max(Comparator.comparing(Visit::getVisitedTime));
                    return latestVisit.map(Visit::getVisitedTime).orElse(LocalDateTime.MIN);
                }).reversed())
                .collect(Collectors.toList());

        return sortedUniqueVisited.stream()
                .map(visited -> VisitIglooResponseDTO.builder()
                        .iglooId(visited.getId())
                        .nickname(visited.getOwner().getNickname())
                        .code(visited.getCode())
                        .build())
                .collect(Collectors.toList());
    }

    // 주어진 List<Visit>에서 중복된 visited를 제거하여 Set<Igloo>로 반환하는 메소드
    private Set<Igloo> getUniqueVisited(List<Visit> visits) {
        Set<Igloo> uniqueVisited = new HashSet<>();
        for (Visit visit : visits) {
            uniqueVisited.add(visit.getVisited());
        }
        return uniqueVisited;
    }
    public List<VisitIglooResponseDTO> showMyVisitedRecords(Igloo myIgloo) {
        List<Visit> distinctRecentVisits = visitRepository.findDistinctRecentVisitsByVisited(myIgloo);
        Set<Igloo> uniqueVisitor = getUniqueVisitor(distinctRecentVisits);

        List<Igloo> sortedUniqueVisitor = uniqueVisitor.stream()
                .sorted(Comparator.comparing(igloo -> {
                    Optional<Visit> latestVisit = distinctRecentVisits.stream()
                            .filter(visit -> visit.getVisitor().equals(igloo))
                            .max(Comparator.comparing(Visit::getVisitedTime));
                    return latestVisit.map(Visit::getVisitedTime).orElse(LocalDateTime.MIN);
                }).reversed())
                .collect(Collectors.toList());

        return sortedUniqueVisitor.stream()
                .map(visited -> VisitIglooResponseDTO.builder()
                        .iglooId(visited.getId())
                        .nickname(visited.getOwner().getNickname())
                        .code(visited.getCode())
                        .build())
                .collect(Collectors.toList());
    }
    private Set<Igloo> getUniqueVisitor(List<Visit> visits) {
        Set<Igloo> uniqueVisited = new HashSet<>();
        for (Visit visit : visits) {
            uniqueVisited.add(visit.getVisitor());
        }
        return uniqueVisited;
    }


}
