package br.com.raynerweb.tracking.mapper;

import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.entity.TrackingLocationEntity;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TrackingLocationMapperTest {

    private TrackingLocationMapper mapper;

    @Before
    public void setup() {
        mapper = new TrackingLocationMapper();
    }

    @Test
    public void testToEntity() {
        TrackingLocationEntity entity = mapper.dtoToEntity(
                new RequestTrackingLocationDto(
                        1L,
                        BigDecimal.valueOf(-15.826691),
                        BigDecimal.valueOf(-47.921822)),
                new VehicleDto(1L,
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "123", "WHITE", "123"));

        assertNotNull(entity);
        assertNotNull(entity.getTrackingDate());
        assertNotNull(entity.getLongitude());
        assertNotNull(entity.getLatitude());
        assertNotNull(entity.getVehicleId());

        assertNull(entity.getId());
    }

    @Test
    public void testToDto() {
        TrackingLocationEntity entity = mapper.dtoToEntity(
                new RequestTrackingLocationDto(
                        1L,
                        BigDecimal.valueOf(-15.826691),
                        BigDecimal.valueOf(-47.921822)),
                new VehicleDto(1L,
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        "123", "WHITE", "123"));
        entity.setId(1L);

        ResponseTrackingLocationDto dto = mapper.entityToDto(entity);
        assertNotNull(dto);
        assertNotNull(dto.trackingDate());
        assertNotNull(dto.latitude());
        assertNotNull(dto.longitude());
        assertNotNull(dto.id());
        assertNotNull(dto.vehicleId());

    }

}
