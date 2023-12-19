package donggukthon.team10.igloo.service.auth;

import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.auth.response.LoginResponseDTO;
import donggukthon.team10.igloo.service.IglooService;
import donggukthon.team10.igloo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final IglooService iglooService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public LoginResponseDTO login(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        User findUser = userService.findByUsername(username);
        return LoginResponseDTO.builder()
                .user(findUser)
                .token(jwtProvider.generateToken(authenticate))
                .code(iglooService.findMyIgloo(findUser).getCode())
                .build();
    }
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}
