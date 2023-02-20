package br.com.raynerweb.tracking.dto.sensor;

import jakarta.validation.constraints.NotNull;

public record RequestTrackingSensorDto(
        @NotNull(message = "Vehicle is required")
        Long vehicleId,
        @NotNull(message = "Sensor is required")
        String sensorId,
        @NotNull(message = "Sensor data is required")
        String data
) {
}
