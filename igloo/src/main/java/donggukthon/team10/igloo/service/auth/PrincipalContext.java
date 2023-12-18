package donggukthon.team10.igloo.service.auth;

import donggukthon.team10.igloo.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
public class PrincipalContext extends org.springframework.security.core.userdetails.User {
    private User user;
    public PrincipalContext(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getId().toString(), user.getPassword(), authorities);
        this.user = user;
    }
}
