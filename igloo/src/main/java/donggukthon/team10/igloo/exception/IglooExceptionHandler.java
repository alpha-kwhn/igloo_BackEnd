package donggukthon.team10.igloo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IglooExceptionHandler {
    @ExceptionHandler(IglooException.class)
    public ResponseEntity<ErrorResponseEntity> handleException(IglooException e){
        return ErrorResponseEntity.toResponseEntity(e.getError());
    }
}
