package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.App;
import br.com.raynerweb.tracking.dto.error.ResponseFieldError;
import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.service.TrackingLocationService;
import com.google.gson.Gson;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

import static br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class TrackinLocationRestTest {

    private MockMvc mvc;
    private String uri;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private TrackingLocationService service;

    @Autowired
    private Gson gson;

    @Before
    public void setUp() {
        uri = "/tracking-location";
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
        RequestTrackingLocationDto dto = new RequestTrackingLocationDto(
                1L,
                BigDecimal.valueOf(new Random().nextDouble()),
                BigDecimal.valueOf(new Random().nextDouble())
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
    public void testLatitudeIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("vehicleId", 1L);
        invalidInput.put("longitude", BigDecimal.valueOf(new Random().nextDouble()));

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
        assertEquals(LATITUDE_REQUIRED, error.message());
        assertEquals("NotNull", error.code());
    }

    @Test
    public void testLongitudeIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("vehicleId", 1L);
        invalidInput.put("latitude", BigDecimal.valueOf(new Random().nextDouble()));

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
        assertEquals(LONGITUDE_REQUIRED, error.message());
        assertEquals("NotNull", error.code());
    }

    @Test
    public void testVehicleIsRequired() throws Exception {
        Map<String, Object> invalidInput = new HashMap<>();
        invalidInput.put("latitude", BigDecimal.valueOf(new Random().nextDouble()));
        invalidInput.put("longitude", BigDecimal.valueOf(new Random().nextDouble()));

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
