package donggukthon.team10.igloo.service.paper;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.RollingPaper;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.paper.request.WritePaperDTO;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.PaperException;
import donggukthon.team10.igloo.repository.IglooRepository;
import donggukthon.team10.igloo.repository.PaperRepository;
import donggukthon.team10.igloo.repository.UserRepository;
import donggukthon.team10.igloo.service.IglooService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final IglooService iglooService;

    @Transactional
    public ApiResponse<RollingPaper> createRollingPaper(WritePaperDTO writePaperDTO, Long iglooId) {
        User creator = userRepository.findUserById(writePaperDTO.id())
                .orElseThrow(() -> new PaperException(CustomErrorCode.NOT_LOGIN_USER));

        RollingPaper paper = RollingPaper.builder()
                .design(writePaperDTO.design())
                .content(writePaperDTO.contents())
                .writer(creator)
                .igloo(iglooService.findById(iglooId))
                .build();

        RollingPaper newPaper = paperRepository.save(paper);

        return ApiResponse.<RollingPaper>builder()
                .data(newPaper)
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }
}
