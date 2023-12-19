package donggukthon.team10.igloo.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final CustomErrorCode error;
    public AuthException(CustomErrorCode error){
        this.error = error;
    }
}
