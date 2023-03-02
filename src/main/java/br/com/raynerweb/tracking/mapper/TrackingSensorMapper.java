package br.com.raynerweb.tracking.mapper;

import br.com.raynerweb.tracking.dto.SensorDto;
import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.dto.sensor.ResponseTrackingSensorDto;
import br.com.raynerweb.tracking.entity.TrackingSensorEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TrackingSensorMapper {

    public TrackingSensorEntity dtoToEntity(RequestTrackingSensorDto dto, VehicleDto vehicleDto, SensorDto sensorDto) {
        TrackingSensorEntity entity = new TrackingSensorEntity();
        entity.setTrackingDate(new Date());
        entity.setVehicleId(vehicleDto.id());
        entity.setSensorId(sensorDto.sensorId());
        entity.setSensorData(dto.data());
        return entity;
    }

    public ResponseTrackingSensorDto entityToDto(TrackingSensorEntity entity) {
        return new ResponseTrackingSensorDto(
                entity.getId(),
                entity.getVehicleId(),
                entity.getSensorId(),
                entity.getSensorData(),
                entity.getTrackingDate()
        );
    }

}
