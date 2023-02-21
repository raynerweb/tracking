package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.entity.TrackingLocationEntity;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import br.com.raynerweb.tracking.mapper.TrackingLocationMapper;
import br.com.raynerweb.tracking.producer.TrackingLocationProducer;
import br.com.raynerweb.tracking.repository.TrackingLocationRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static br.com.raynerweb.tracking.service.VehicleServiceTest.vehicleDto;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
public class TrackingLocationServiceTest {

    @InjectMocks
    private TrackingLocationService service;

    @Mock
    private TrackingLocationRepository repository;

    @Mock
    private TrackingLocationProducer producer;

    @Mock
    private TrackingLocationMapper mapper;

    @Mock
    private VehicleService vehicleService;

    @Test
    public void testSaveAsync() {
        Mockito.doNothing().when(producer).send(request());
        service.saveAsync(request());
    }

    @Test
    public void testSave() {
        when(vehicleService.findById(anyLong())).thenReturn(vehicleDto());
        when(mapper.dtoToEntity(request(), vehicleDto())).thenReturn(entity());
        when(repository.save(any())).thenReturn(saved());
        service.save(request());
    }

    @Test
    public void testNotFoundLogger() {
        when(vehicleService.findById(anyLong())).thenThrow(new NotFoundException("NotFound"));
        service.save(request());
    }

    @Test
    public void testInternalServerErrorLogger() {
        when(vehicleService.findById(anyLong())).thenThrow(new InternalServerErrorException("Internal Server Error"));
        service.save(request());
    }

    @Test
    public void testFindAll() {
        when(repository.findAll()).thenReturn(list());
        when(mapper.entityToDto(saved())).thenReturn(response());
        List<ResponseTrackingLocationDto> all = service.findAll();
        assertNotNull(all);
    }

    private ResponseTrackingLocationDto response() {
        return new ResponseTrackingLocationDto(1L, 1L, BigDecimal.valueOf(-15.826691), BigDecimal.valueOf(-47.921822), new Date());
    }

    private RequestTrackingLocationDto request() {
        return new RequestTrackingLocationDto(1L, BigDecimal.valueOf(-15.826691), BigDecimal.valueOf(-47.921822));
    }

    private TrackingLocationEntity entity() {
        TrackingLocationEntity entity = new TrackingLocationEntity();
        entity.setTrackingDate(new Date());
        entity.setVehicleId(1L);
        entity.setLongitude(BigDecimal.valueOf(-47.921822));
        entity.setLatitude(BigDecimal.valueOf(-15.826691));
        return entity;
    }

    private TrackingLocationEntity saved() {
        TrackingLocationEntity entity = entity();
        entity.setId(1L);
        return entity;
    }

    private List<TrackingLocationEntity> list() {
        return Lists.list(saved());
    }

}
