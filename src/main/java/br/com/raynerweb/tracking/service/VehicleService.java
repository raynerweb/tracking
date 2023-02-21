package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${vehicle.uri}")
    private String uri;

    public VehicleDto findById(Long id) {
        Map<String, Long> variables = new HashMap<>();
        variables.putIfAbsent("id", id);
        try {
            ResponseEntity<VehicleDto> forEntity = restTemplate.getForEntity(uri, VehicleDto.class, variables);
            return forEntity.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Vehicle not found.");
        } catch (HttpClientErrorException e) {
            String uuid = UUID.randomUUID().toString();
            String message = "Something went wrong. Contact the support team and tell them this identifier: %s".formatted(uuid);
            LOGGER.error(message + System.lineSeparator() + "VehicleId %s".formatted(id), e);
            throw new InternalServerErrorException(message);
        }
    }

}
