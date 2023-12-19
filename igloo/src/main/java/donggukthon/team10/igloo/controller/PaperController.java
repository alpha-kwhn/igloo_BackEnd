package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.dto.paper.request.OpenDateDTO;
import donggukthon.team10.igloo.dto.paper.request.PaperChangeDTO;
import donggukthon.team10.igloo.dto.paper.request.WritePaperDTO;
import donggukthon.team10.igloo.dto.paper.response.CreatedRollingPaperDTO;
import donggukthon.team10.igloo.dto.paper.response.DeleteResultDTO;
import donggukthon.team10.igloo.dto.paper.response.FindRollingPaperDTO;
import donggukthon.team10.igloo.dto.paper.response.OpenedPaper;
import donggukthon.team10.igloo.service.paper.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping("/{iglooId}")
    public ApiResponse<List<FindRollingPaperDTO>> getRollingPapers(@PathVariable("iglooId") Long iglooId) {
        return ApiResponse.<List<FindRollingPaperDTO>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(paperService.getRollingPapers(iglooId))
                .build();
    }

    @PostMapping("/{iglooId}")
    public ApiResponse<CreatedRollingPaperDTO> writeRollingPaper(@PathVariable("iglooId") Long iglooId, @RequestBody WritePaperDTO paper) {
        return paperService.createRollingPaper(paper, iglooId);
    }

    @GetMapping("/{iglooId}/")
    public ApiResponse<OpenedPaper> openRollingPaper(@PathVariable("iglooId") Long iglooId,
                                                     @RequestParam(name = "paperId") Long paperId,
                                                     @RequestBody OpenDateDTO date) {
        return paperService.openPaper(iglooId, paperId, date.date());
    }

    @DeleteMapping("/{iglooId}/")
    public ApiResponse<DeleteResultDTO> deletePaper(@PathVariable("iglooId") Long iglooId,
                                                    @RequestParam(name = "paperId") Long paperId) {
        return paperService.deletePaper(iglooId, paperId);
    }

    @PatchMapping("/{iglooId}/")
    public ApiResponse<?> updatePaper(@PathVariable("iglooId") Long iglooId,
                                                   @RequestParam(name = "paperId") Long paperId,
                                                   @RequestBody PaperChangeDTO changDTO) {

        return paperService.updatePaper(iglooId, paperId, changDTO);
    }
}
