package e101.hishop.global.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {
    private T data;
    private Integer errorCode;
    private String errorMessage;
}
