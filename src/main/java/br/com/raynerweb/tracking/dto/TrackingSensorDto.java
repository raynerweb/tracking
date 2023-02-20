package br.com.raynerweb.tracking.dto;

public record TrackingSensorDto(
        Long vehicleId,
        String sensorId,
        String data
) {
}
