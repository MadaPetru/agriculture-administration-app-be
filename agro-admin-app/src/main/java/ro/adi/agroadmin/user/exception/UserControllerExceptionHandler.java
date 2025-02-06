package ro.adi.agroadmin.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.adi.agroadmin.common.exception.handler.CommonControllerExceptionHandler;

@RestControllerAdvice
public class UserControllerExceptionHandler extends CommonControllerExceptionHandler {

    @ExceptionHandler(value = UserEmailAlreadyUsedException.class)
    public ResponseEntity<Object> exception(UserEmailAlreadyUsedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
