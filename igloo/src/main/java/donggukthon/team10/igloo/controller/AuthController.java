package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.common.ApiResponse;
import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.auth.response.LoginResponse;
import donggukthon.team10.igloo.dto.auth.response.OauthInfoResponse;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.AuthException;
import donggukthon.team10.igloo.service.IglooService;
import donggukthon.team10.igloo.service.UserService;
import donggukthon.team10.igloo.service.auth.GoogleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final GoogleService googleService;
    private final IglooService iglooService;

    @GetMapping("/oauth")
    public void startOauth(HttpServletResponse response){
        try {
            response.sendRedirect(googleService.getCallbackUrl());
        } catch (IOException e) {
            throw new AuthException(CustomErrorCode.GOOGLE_PATH_ERROR);
        }
    }

//    @GetMapping("/goo")
//    public ApiResponse<LoginResponse> getCallback(@RequestParam(name = "code") String code) {
//        String accessToken = googleService.getAccessCode(code);
//        OauthInfoResponse oauthInfoResponse = googleService.requestUserInfo(accessToken);

//
//        if (user == null) {
//            user = userService.saveUser(oauthInfoResponse);
//            iglooService.generateIgloo(user);
//        }
//        return userService.login(user);
    }

//    @PostMapping("/logout")
//    public ApiResponse<?> logout() {
//        return userService.logout();
//    }
//}
