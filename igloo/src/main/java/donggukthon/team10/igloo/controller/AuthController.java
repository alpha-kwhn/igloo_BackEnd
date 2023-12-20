package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.auth.request.LoginDTO;
import donggukthon.team10.igloo.dto.auth.response.LoginResponseDTO;
import donggukthon.team10.igloo.dto.user.request.SaveUserDTO;
import donggukthon.team10.igloo.service.IglooService;
import donggukthon.team10.igloo.service.UserService;
import donggukthon.team10.igloo.service.auth.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final IglooService iglooService;
    private static final String BEARER_PREFIX = "Bearer ";

    @PostMapping("/register")
    public ApiResponse<?> saveUser(@RequestBody SaveUserDTO saveUserDTO){
        User savedUser = userService.saveUser(saveUserDTO);
        iglooService.generateIgloo(savedUser);
        return ApiResponse.nullDataBuilder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }
    @GetMapping("/check")
    public ApiResponse<Object> isAvailable(@RequestParam String id){
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(userService.isAvailableId(id))
                .build();
    }
    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        LoginResponseDTO loginResponseDTO = loginService.login(loginDTO.getId(), loginDTO.getPassword());
        response.setHeader("Authorization", BEARER_PREFIX + loginResponseDTO.getToken());
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(loginResponseDTO.getCode())
                .build();
    }
    @GetMapping("/logout")
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response){
        loginService.logout(request, response);
        return ApiResponse.nullDataBuilder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
