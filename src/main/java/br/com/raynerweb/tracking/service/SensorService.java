package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.SensorDto;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SensorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);

    @Value("${sensor.uri}")
    private String uri;

    public SensorDto findById(Long id) {
        Map<String, Long> variables = new HashMap<>();
        variables.putIfAbsent("id", id);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<SensorDto> forEntity = restTemplate.getForEntity(uri, SensorDto.class, variables);
            return forEntity.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Sensor not found.");
        } catch (HttpClientErrorException e) {
            String uuid = UUID.randomUUID().toString();
            String message = "Something went wrong. Contact the support team and tell them this identifier: %s".formatted(uuid);
            LOGGER.error(message + System.lineSeparator() + "SensorId %s".formatted(id), e);
            throw new InternalServerErrorException(message);
        }
    }
}
