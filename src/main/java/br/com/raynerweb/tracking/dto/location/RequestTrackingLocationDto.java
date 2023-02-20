package br.com.raynerweb.tracking.dto.location;

import java.math.BigDecimal;

public record RequestTrackingLocationDto(
        Long vehicleId,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
