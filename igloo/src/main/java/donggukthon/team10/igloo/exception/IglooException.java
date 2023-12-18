package donggukthon.team10.igloo.exception;

import lombok.Getter;

@Getter
public class IglooException extends RuntimeException{
    private final CustomErrorCode error;
    public IglooException(CustomErrorCode error){
        this.error = error;
    }
}
