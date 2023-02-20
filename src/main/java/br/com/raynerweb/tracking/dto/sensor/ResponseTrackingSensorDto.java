package br.com.raynerweb.tracking.dto.sensor;

import java.util.Date;

public record ResponseTrackingSensorDto(
        Long id,
        Long vehicleId,
        String sensorId,
        String data,
        Date trackingDate
) {
}
