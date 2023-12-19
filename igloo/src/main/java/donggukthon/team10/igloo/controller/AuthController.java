package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.dto.user.request.SaveUserDTO;
import donggukthon.team10.igloo.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ApiResponse saveUser(@RequestBody SaveUserDTO saveUserDTO){
        userService.saveUser(saveUserDTO);
        return ApiResponse.nullDataBuilder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }
    @GetMapping("/check")
    public ApiResponse isAvailable(@RequestParam String id){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(userService.isAvailableId(id))
                .build();
    }
}
