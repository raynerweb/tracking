package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.SensorDto;
import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService service;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "uri", "http://someUri");
    }

    @Test
    public void testSuccessful() {
        when(restTemplate.getForEntity(anyString(), eq(VehicleDto.class), anyMap())).thenReturn(dto());
        VehicleDto dto = service.findById(1L);
        assertNotNull(dto);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFound() {
        when(restTemplate.getForEntity(anyString(), eq(VehicleDto.class), anyMap()))
                .thenThrow(
                        HttpClientErrorException.create(HttpStatus.NOT_FOUND,
                                "not found", null, null, null));
        service.findById(1L);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testInternalServerError() {
        when(restTemplate.getForEntity(anyString(), eq(VehicleDto.class), anyMap()))
                .thenThrow(
                        HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR,
                                "Internal server error", null, null, null));
        service.findById(1L);
    }

    private ResponseEntity<VehicleDto> dto() {
        return new ResponseEntity<>(
                vehicleDto(),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public static VehicleDto vehicleDto() {
        return new VehicleDto(1L,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "123", "WHITE", "123");
    }
}
