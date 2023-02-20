package br.com.raynerweb.tracking.dto;

import java.math.BigDecimal;

public record TrackingLocationDto(
        Long vehicleId,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
