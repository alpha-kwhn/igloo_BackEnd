package donggukthon.team10.igloo.service.auth;

import donggukthon.team10.igloo.domain.User;
import donggukthon.team10.igloo.exception.CustomErrorCode;
import donggukthon.team10.igloo.exception.IglooException;
import donggukthon.team10.igloo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        User user = userRepository.findById(username)
                .orElseThrow(() -> new IglooException(CustomErrorCode.NOT_FOUND_USER));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new PrincipalContext(user, authorities);
    }
}
