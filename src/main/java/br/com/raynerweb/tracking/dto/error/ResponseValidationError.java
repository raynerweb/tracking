package br.com.raynerweb.tracking.dto.error;

import java.util.Date;
import java.util.List;

public record ResponseValidationError(
        String path,
        String error,
        String message,
        Date timestamp,
        int status,
        List<ResponseFieldError> fields
) {
}
