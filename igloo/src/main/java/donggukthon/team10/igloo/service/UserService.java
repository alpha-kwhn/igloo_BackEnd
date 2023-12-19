package donggukthon.team10.igloo.service;

import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.dto.TestLoginDTO;
import donggukthon.team10.igloo.dto.user.request.SaveUserDTO;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.IglooException;
import donggukthon.team10.igloo.repository.UserRepository;
import donggukthon.team10.igloo.service.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * 아래의 3가지 method는 모두 test용 method입니다!!!
     * 진행하실 때 변경 반드시 해야합니다!
     */
    @Transactional
    public void saveUser(SaveUserDTO saveUserDTO){
        userRepository.save(User.builder()
                .username(saveUserDTO.getId())
                .password(passwordEncoder.encode(saveUserDTO.getPassword()))
                .korName(saveUserDTO.getKorName())
                .nickname(saveUserDTO.getNickname())
                .build());
    }
    public boolean isAvailableId(String username){
        return !userRepository.existsByUsername(username);
    }
    public String login(TestLoginDTO testLoginDTO){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(testLoginDTO.getUserId().toString(), testLoginDTO.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtProvider.generateToken(authenticate);
    }
    public User getLoginUser(){
        return userRepository.findUserByNickname(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_USER));
    }
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_USER));
    }
    private String generateNickname(){
        return "dlawjddn";
    }
}
