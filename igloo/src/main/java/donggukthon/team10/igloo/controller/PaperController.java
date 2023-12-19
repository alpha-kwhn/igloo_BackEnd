package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.RollingPaper;
import donggukthon.team10.igloo.dto.paper.request.WritePaperDTO;
import donggukthon.team10.igloo.service.paper.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

    @GetMapping("/{iglooId}")
    public ApiResponse<?> getRollingPapers(@PathVariable String iglooId) {
        return null;
    }

    @PostMapping("/{iglooId}")
    public ApiResponse<RollingPaper> writeRollingPapaer(@PathVariable Long iglooId, @RequestBody WritePaperDTO paper) {
        return paperService.createRollingPaper(paper, iglooId);
    }
}
