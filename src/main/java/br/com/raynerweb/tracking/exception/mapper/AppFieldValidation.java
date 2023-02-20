package br.com.raynerweb.tracking.exception.mapper;

public record AppFieldValidation(
        String attribute,
        String message,
        String code
) {
}
