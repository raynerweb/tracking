package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.dto.sensor.ResponseTrackingSensorDto;
import br.com.raynerweb.tracking.entity.TrackingSensorEntity;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import br.com.raynerweb.tracking.mapper.TrackingSensorMapper;
import br.com.raynerweb.tracking.producer.TrackingSensorProducer;
import br.com.raynerweb.tracking.repository.TrackingSensorRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static br.com.raynerweb.tracking.service.SensorServiceTest.sensorDto;
import static br.com.raynerweb.tracking.service.VehicleServiceTest.vehicleDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
public class TrackingSensorServiceTest {

    @InjectMocks
    private TrackingSensorService service;

    @Mock
    private TrackingSensorRepository repository;

    @Mock
    private TrackingSensorProducer producer;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private SensorService sensorService;

    @Mock
    private TrackingSensorMapper mapper;

    @Test
    public void testFindAll() {
        when(repository.findAll()).thenReturn(list());

        List<ResponseTrackingSensorDto> all = service.findAll();

        assertNotNull(all);
        assertEquals(1, all.size());
    }

    @Test
    public void testSaveAsync() {
        Mockito.doNothing().when(producer).send(request());
        service.saveAsync(request());
    }

    @Test
    public void testSave() {
        when(vehicleService.findById(anyLong())).thenReturn(vehicleDto());
        when(sensorService.findById(anyString())).thenReturn(sensorDto());
        when(repository.save(any())).thenReturn(entity());
        service.save(request());
    }

    @Test
    public void testVehicleNotFound() {
        when(vehicleService.findById(anyLong())).thenThrow(new NotFoundException("Not Found"));
        service.save(request());
    }

    @Test
    public void testInternalServerError() {
        when(vehicleService.findById(anyLong())).thenThrow(new InternalServerErrorException("500"));
        service.save(request());
    }

    private TrackingSensorEntity entity() {
        TrackingSensorEntity entity = new TrackingSensorEntity();
        entity.setSensorData("data");
        entity.setTrackingDate(new Date());
        entity.setSensorId(UUID.randomUUID().toString());
        entity.setVehicleId(1L);
        return entity;
    }

    private List<TrackingSensorEntity> list() {
        return Lists.list(entity());
    }

    private RequestTrackingSensorDto request() {
        return new RequestTrackingSensorDto(
                1L,
                UUID.randomUUID().toString(),
                "100"
        );
    }
}
