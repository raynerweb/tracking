package br.com.raynerweb.tracking.dto.error;

public record ResponseFieldError(
        String message,
        String code
) {
}
