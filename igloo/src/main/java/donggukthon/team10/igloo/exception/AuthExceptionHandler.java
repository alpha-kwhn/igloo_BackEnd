package donggukthon.team10.igloo.exception;

import donggukthon.team10.igloo.common.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ApiResponse<?> authException(AuthException e) {
        return ApiResponse.nullDataBuilder()
                .code(e.getError().getCode().value())
                .message(e.getMessage())
                .build();
    }
}
