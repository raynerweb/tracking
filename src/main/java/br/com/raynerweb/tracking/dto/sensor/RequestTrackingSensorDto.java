package br.com.raynerweb.tracking.dto.sensor;

import jakarta.validation.constraints.NotNull;

public record RequestTrackingSensorDto(
        @NotNull(message = VEHICLE_REQUIRED)
        Long vehicleId,
        @NotNull(message = SENSOR_REQUIRED)
        String sensorId,
        @NotNull(message = SENSOR_DATA_REQUIRED)
        String data
) {
    public static final String VEHICLE_REQUIRED = "Vehicle is required";
    public static final String SENSOR_REQUIRED = "Sensor is required";
    public static final String SENSOR_DATA_REQUIRED = "Sensor data is required";
}
