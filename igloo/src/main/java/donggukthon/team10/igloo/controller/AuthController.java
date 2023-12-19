package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.dto.auth.request.LoginDTO;
import donggukthon.team10.igloo.dto.user.request.SaveUserDTO;
import donggukthon.team10.igloo.service.UserService;
import donggukthon.team10.igloo.service.auth.LoginService;
import jakarta.servlet.http.HttpServletResponse;
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
    private final LoginService loginService;
    private static final String BEARER_PREFIX = "Bearer ";

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
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        response.setHeader("Authorization", BEARER_PREFIX + loginService.login(loginDTO.getId(), loginDTO.getPassword()));
        return ApiResponse.nullDataBuilder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
