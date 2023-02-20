package br.com.raynerweb.tracking.dto.sensor;

public record RequestTrackingSensorDto(
        Long vehicleId,
        String sensorId,
        String data
) {
}
