package donggukthon.team10.igloo.exception;

import lombok.Getter;

@Getter
public class PaperException extends RuntimeException {
    private final CustomErrorCode error;
    public PaperException(CustomErrorCode error) { this.error = error; }
}
