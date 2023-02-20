package br.com.raynerweb.tracking.dto;

public record VehicleDto(
        Long id,
        String customerId,
        String telemetryProfileId,
        String driverId,
        String numPlate,
        String color,
        String vin
) {
}
