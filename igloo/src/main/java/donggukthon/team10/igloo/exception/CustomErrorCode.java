package donggukthon.team10.igloo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    ;
    //400


    //401


    //404

    private final HttpStatus code;
    private final String errMessage;

    CustomErrorCode(HttpStatus code, String errMesseage) {
        this.code = code;
        this.errMessage = errMesseage;
    }
}
