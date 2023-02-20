package br.com.raynerweb.tracking.dto.location;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTrackingLocationDto(
        @NotNull(message = "Vehicle is required")
        Long vehicleId,
        @NotNull(message = "Latitude is required")
        BigDecimal latitude,
        @NotNull(message = "Longitude is required")
        BigDecimal longitude
) {
}
