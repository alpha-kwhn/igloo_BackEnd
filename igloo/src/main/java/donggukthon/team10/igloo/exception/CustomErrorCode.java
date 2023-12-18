package donggukthon.team10.igloo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {

    //400


    //401
    NOT_LOGIN_USER(HttpStatus.UNAUTHORIZED, "NOT LOGIN USER"),

    //403
    MUST_BE_SAME(HttpStatus.FORBIDDEN, "EDITER AND WRITER DOESN't SAME"),

    //404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "NOT FOUND USER CHECK YOUR INFO PLZ"),
    NOT_FOUND_IGLOO(HttpStatus.NOT_FOUND, "NOT FOUND IGLOO"),
    NOT_FOUND_QUIZ(HttpStatus.NOT_FOUND, "NOT FOUND QUIZ"),

    ;

    private final HttpStatus code;
    private final String errMessage;

    CustomErrorCode(HttpStatus code, String errMessage) {
        this.code = code;
        this.errMessage = errMessage;
    }
}
