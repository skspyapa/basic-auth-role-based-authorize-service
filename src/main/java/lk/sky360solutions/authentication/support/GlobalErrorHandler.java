package lk.sky360solutions.authentication.support;

import lk.sky360solutions.authentication.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
      ProductNotFoundException.class
    })
    public Map<String, String> handleNotFoundError(Exception e) {
        return Collections.singletonMap("error", e.getMessage());
    }
}
