package br.com.raynerweb.tracking.mapper;

import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.entity.TrackingLocationEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TrackingLocationMapper {

    public TrackingLocationEntity dtoToEntity(RequestTrackingLocationDto dto, VehicleDto vehicleDto) {
        TrackingLocationEntity entity = new TrackingLocationEntity();
        entity.setLatitude(dto.latitude());
        entity.setLongitude(dto.longitude());
        entity.setTrackingDate(new Date());
        entity.setVehicleId(vehicleDto.id());
        return entity;
    }

    public ResponseTrackingLocationDto entityToDto(TrackingLocationEntity entity) {
        return new ResponseTrackingLocationDto(
                entity.getId(),
                entity.getVehicleId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getTrackingDate()
        );
    }
}
