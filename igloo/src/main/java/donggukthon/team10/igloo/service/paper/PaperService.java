package donggukthon.team10.igloo.service.paper;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.RollingPaper;
import donggukthon.team10.igloo.dto.paper.request.WritePaperDTO;
import donggukthon.team10.igloo.dto.paper.response.CreatedRollingPaperDTO;
import donggukthon.team10.igloo.dto.paper.response.FindRollingPaperDTO;
import donggukthon.team10.igloo.dto.paper.response.OpenedPaper;
import donggukthon.team10.igloo.dto.paper.response.PaperUserInfo;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.PaperException;
import donggukthon.team10.igloo.repository.PaperRepository;
import donggukthon.team10.igloo.service.IglooService;
import donggukthon.team10.igloo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaperService {

    private final PaperRepository paperRepository;
    private final IglooService iglooService;
    private final UserService userService;

    @Transactional
    public ApiResponse<CreatedRollingPaperDTO> createRollingPaper(WritePaperDTO writePaperDTO, Long iglooId) {
        RollingPaper paper = RollingPaper.builder()
                .design(writePaperDTO.design())
                .content(writePaperDTO.contents())
                .writer(userService.findById(writePaperDTO.userId()))
                .igloo(iglooService.findById(iglooId))
                .build();

        if (paperRepository.findRollingPaperByWriterAndIgloo(paper.getIgloo().getId(), paper.getWriter().getId()).isEmpty()) {
            paper = paperRepository.save(paper);
            CreatedRollingPaperDTO created = CreatedRollingPaperDTO.builder()
                    .paperId(paper.getId())
                    .creator(PaperUserInfo.builder()
                            .korName(paper.getWriter().getKorName())
                            .nickname(paper.getWriter().getNickname())
                            .userId(paper.getWriter().getId())
                            .build())
                    .design(paper.getDesign())
                    .build();
            return ApiResponse.<CreatedRollingPaperDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message(HttpStatus.CREATED.getReasonPhrase())
                    .data(created)
                    .build();
        } else {
            return ApiResponse.<CreatedRollingPaperDTO>nullDataBuilder()
                    .code(HttpStatus.CONFLICT.value())
                    .message(HttpStatus.CONFLICT.getReasonPhrase())
                    .build();
        }
    }

    @Transactional
    public List<FindRollingPaperDTO> getRollingPapers(Long iglooId) {
        return paperRepository
                .findAllRollingPapersByIgloo(iglooService.findById(iglooId))
                .orElseThrow(() -> new PaperException(CustomErrorCode.NOT_FOUND_IGLOO))
                .stream()
                .map(paper -> {
                    return FindRollingPaperDTO.builder()
                            .design(paper.getDesign())
                            .paperId(paper.getId())
                            .writer(PaperUserInfo.builder()
                                    .korName(paper.getWriter().getKorName())
                                    .nickname(paper.getWriter().getNickname())
                                    .userId(paper.getWriter().getId())
                                    .build()
                            )
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ApiResponse<OpenedPaper> openPaper(Long iglooId, Long paperId, Date date) {
        LocalDate comparisonDate = LocalDate.of(2024, 1, 1);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (localDate.isEqual(comparisonDate) || localDate.isAfter(comparisonDate)) {
            RollingPaper rollingPaper = paperRepository.findRollingPaperByIglooIdAndPaperId(iglooId, paperId)
                    .orElseThrow(() -> new PaperException(CustomErrorCode.NOT_FOUND_PAPER));

            return ApiResponse.<OpenedPaper>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(OpenedPaper.builder()
                            .canRead(true)
                            .contents(rollingPaper.getContent())
                            .build())
                    .build();
        } else {
            return ApiResponse.<OpenedPaper>builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                    .data(OpenedPaper.builder()
                            .canRead(false)
                            .contents(null)
                            .build())
                    .build();
        }
    }
}
