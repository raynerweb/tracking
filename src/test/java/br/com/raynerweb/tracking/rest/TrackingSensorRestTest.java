package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.error.ResponseFieldError;
import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.service.TrackingSensorService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto.VEHICLE_REQUIRED;
import static br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto.SENSOR_DATA_REQUIRED;
import static br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto.SENSOR_REQUIRED;
import static org.junit.Assert.*;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = TrackingSensorRest.class)
public class TrackingSensorRestTest {

    @Autowired
    private MockMvc mvc;
    private String uri;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @MockBean
    private TrackingSensorService service;

    @Autowired
    private Gson gson;

    @Before
    public void setUp() {
        uri = "/tracking-sensor";
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAll() throws Exception {
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .get(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertTrue(StringUtils.hasText(content));
        assertEquals("[]", content);
    }

    @Test
    public void testSaveSuccessfully() throws Exception {
        RequestTrackingSensorDto dto = new RequestTrackingSensorDto(
                1L,
                UUID.randomUUID().toString(),
                "DATA"
        );
        String payload = gson.toJson(dto);

        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(payload))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertFalse(StringUtils.hasText(content));
    }

    @Test
    public void testSensorIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("vehicleId", 1L);
        invalidInput.put("data", "DATA");

        String payload = gson.toJson(invalidInput);

        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(payload))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResponseValidationError response = gson.fromJson(content, ResponseValidationError.class);

        assertEquals(400, response.status());
        assertNotNull(response);

        ResponseFieldError error = response.fields().stream().findAny().orElseThrow(() -> new RuntimeException("Falha no teste"));
        assertEquals(SENSOR_REQUIRED, error.message());
        assertEquals("NotNull", error.code());
    }

    @Test
    public void testSensorDataIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("vehicleId", 1L);
        invalidInput.put("sensorId", UUID.randomUUID().toString());

        String payload = gson.toJson(invalidInput);

        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(payload))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResponseValidationError response = gson.fromJson(content, ResponseValidationError.class);

        assertEquals(400, response.status());
        assertNotNull(response);

        ResponseFieldError error = response.fields().stream().findAny().orElseThrow(() -> new RuntimeException("Falha no teste"));
        assertEquals(SENSOR_DATA_REQUIRED, error.message());
        assertEquals("NotNull", error.code());
    }

    @Test
    public void testVehicleIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("sensorId", UUID.randomUUID().toString());
        invalidInput.put("data", "DATA");

        String payload = gson.toJson(invalidInput);

        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(payload))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResponseValidationError response = gson.fromJson(content, ResponseValidationError.class);

        assertEquals(400, response.status());
        assertNotNull(response);

        ResponseFieldError error = response.fields().stream().findAny().orElseThrow(() -> new RuntimeException("Falha no teste"));
        assertEquals(VEHICLE_REQUIRED, error.message());
        assertEquals("NotNull", error.code());
    }
}
