package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.SensorDto;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
public class SensorServiceTest {

    private static final String SENSOR_ID = "3e55d601-6f9c-4208-998b-d4c49b48830a";
    private static final String SENSOR_NAME = "RPM";
    @InjectMocks
    private SensorService service;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "uri", "http://someUri");
    }

    @Test
    public void testRequestSuccessful() {
        when(restTemplate.getForEntity(anyString(), eq(SensorDto.class), anyMap())).thenReturn(success());
        SensorDto dto = service.findById(SENSOR_ID);
        assertNotNull(dto);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFound() {
        when(restTemplate.getForEntity(anyString(), eq(SensorDto.class), anyMap()))
                .thenThrow(
                        HttpClientErrorException.create(HttpStatus.NOT_FOUND,
                                "not found", null, null, null));
        service.findById(SENSOR_ID);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testInternalServerError() {
        when(restTemplate.getForEntity(anyString(), eq(SensorDto.class), anyMap()))
                .thenThrow(
                        HttpClientErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR,
                                "Internal server error", null, null, null));
        service.findById(SENSOR_ID);
    }

    private ResponseEntity<SensorDto> success() {
        return new ResponseEntity<>(sensorDto(), HttpStatusCode.valueOf(HttpStatus.OK.value())
        );
    }

    public static SensorDto sensorDto() {
        return new SensorDto(
                SENSOR_ID,
                SENSOR_NAME);
    }

}
