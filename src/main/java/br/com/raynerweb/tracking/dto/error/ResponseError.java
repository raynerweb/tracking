package br.com.raynerweb.tracking.dto.error;

import java.util.Date;

public record ResponseError(
        String path,
        String error,
        String message,
        Date timestamp,
        int status
) {
}
