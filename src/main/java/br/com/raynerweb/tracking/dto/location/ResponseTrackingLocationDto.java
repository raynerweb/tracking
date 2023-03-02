package br.com.raynerweb.tracking.dto.location;

import java.math.BigDecimal;
import java.util.Date;

public record ResponseTrackingLocationDto(
        Long id,
        Long vehicleId,
        BigDecimal latitude,
        BigDecimal longitude,
        Date trackingDate
) {
}
