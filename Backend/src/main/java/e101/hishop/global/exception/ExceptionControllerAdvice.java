package e101.hishop.global.exception;

import e101.hishop.global.common.CommonException;
import e101.hishop.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity commonExHandle(CommonException ex) {
        log.warn("Exception Name = {}, Code = {}, Message = {}", ex.getClass().getName(), ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus())
                .body(CommonResponse.builder().errorCode(ex.getErrorCode()).errorMessage(ex.getErrorMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity accessDeniedExHandle(AccessDeniedException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(CommonResponse.builder().errorCode(3).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity methodNotAllowedExHandle(HttpRequestMethodNotSupportedException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CommonResponse.builder().errorCode(405).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity illegalArgumentExceptionExHandle(IllegalArgumentException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(400).errorMessage(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity constraintViolationExceptionExceptionExHandle(ConstraintViolationException ex) {
        log.warn("Exception Name = {}, Message = {}", ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(5).errorMessage("중복된 값입니다.").build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity processValidationError(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder().errorCode(6).errorMessage("유효하지 않은 값입니다.").build());
    }
}
