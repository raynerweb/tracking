package br.com.raynerweb.tracking.exception.mapper;

import br.com.raynerweb.tracking.dto.error.ResponseFieldError;
import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class AppExceptionMapper {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseValidationError handle(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return getResponseValidarionError(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(), "Input Validation",
                map(ex.getBindingResult().getFieldErrors()));
    }

    public ResponseValidationError getResponseValidarionError(
            HttpStatus httpStatus, String requestURI, String message,
            List<ResponseFieldError> errors) {
        return new ResponseValidationError(
                requestURI,
                httpStatus.getReasonPhrase(),
                message,
                new Date(),
                httpStatus.value(),
                errors
        );
    }

    private List<ResponseFieldError> map(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(fieldError -> new ResponseFieldError(
                        messageSource.getMessage(fieldError, Locale.getDefault()),
                        fieldError.getCode()))
                .toList();
    }

}
