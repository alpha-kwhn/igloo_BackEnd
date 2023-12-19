package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.dto.user.request.IglooPageDTO;
import donggukthon.team10.igloo.dto.user.request.UpdateInfoDTO;
import donggukthon.team10.igloo.dto.user.request.UpdateNicknameDTO;
import donggukthon.team10.igloo.service.IglooService;
import donggukthon.team10.igloo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final IglooService iglooService;
    @PatchMapping("/nickname")
    public ApiResponse updateNickname(@RequestBody UpdateNicknameDTO updateNicknameDTO){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(userService.updateNickname(updateNicknameDTO.getNickname()))
                .build();
    }
    @PatchMapping("/info")
    public ApiResponse updateInfo(@RequestBody UpdateInfoDTO updateInfoDTO){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(userService.updateInfo(updateInfoDTO))
                .build();
    }
    @GetMapping("/mainPage")
    public ApiResponse showIgloo(@RequestBody IglooPageDTO iglooPageDTO){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(iglooService.showIglooInfo(iglooPageDTO.getCode()))
                .build();
    }
}
