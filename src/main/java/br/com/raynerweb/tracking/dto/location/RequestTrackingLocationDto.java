package br.com.raynerweb.tracking.dto.location;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTrackingLocationDto(
        @NotNull(message = VEHICLE_REQUIRED)
        Long vehicleId,
        @NotNull(message = LATITUDE_REQUIRED)
        BigDecimal latitude,
        @NotNull(message = LONGITUDE_REQUIRED)
        BigDecimal longitude
) {

    public static final String VEHICLE_REQUIRED = "Vehicle is required";
    public static final String LATITUDE_REQUIRED = "Latitude is required";
    public static final String LONGITUDE_REQUIRED = "Longitude is required";
}
