package e101.hishop.global.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public CommonException(Integer errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public CommonException(Integer errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus == null ? HttpStatus.BAD_REQUEST : httpStatus;
    }
}
