package donggukthon.team10.igloo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {

    //400


    //401
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "NOT LOGIN USER"),

    //404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "NOT FOUND USER CHECK YOUR INFO PLZ")

    ;

    private final HttpStatus code;
    private final String errMessage;

    CustomErrorCode(HttpStatus code, String errMessage) {
        this.code = code;
        this.errMessage = errMessage;
    }
}
