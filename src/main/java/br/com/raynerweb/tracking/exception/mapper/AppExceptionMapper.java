package br.com.raynerweb.tracking.exception.mapper;

import br.com.raynerweb.tracking.dto.error.ResponseError;
import br.com.raynerweb.tracking.dto.error.ResponseFieldError;
import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import br.com.raynerweb.tracking.exception.BadRequestException;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
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


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseError handle(DataIntegrityViolationException ex, HttpServletRequest request) {
        return getResponseError(HttpStatus.CONFLICT, request.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseError handle(BadRequestException ex, HttpServletRequest request) {
        return getResponseError(HttpStatus.BAD_REQUEST, request.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseError handle(NotFoundException ex, HttpServletRequest request) {
        return getResponseError(HttpStatus.NOT_FOUND, request.getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseError handle(InternalServerErrorException ex, HttpServletRequest request) {
        return getResponseError(HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI(), ex.getMessage());
    }

    public ResponseError getResponseError(HttpStatus httpStatus, String requestURI, String message) {
        return new ResponseError(
                requestURI,
                httpStatus.getReasonPhrase(),
                message,
                new Date(),
                httpStatus.value()
        );
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
