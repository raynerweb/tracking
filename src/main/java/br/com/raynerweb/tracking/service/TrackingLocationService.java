package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import br.com.raynerweb.tracking.mapper.TrackingLocationMapper;
import br.com.raynerweb.tracking.producer.TrackingLocationProducer;
import br.com.raynerweb.tracking.repository.TrackingLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingLocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingLocationService.class);

    @Autowired
    private TrackingLocationRepository repository;

    @Autowired
    private TrackingLocationProducer producer;

    @Autowired
    private TrackingLocationMapper mapper;

    @Autowired
    private VehicleService vehicleService;

    public List<ResponseTrackingLocationDto> findAll() {
        return repository.findAll().stream().map(mapper::entityToDto).toList();
    }

    public void save(RequestTrackingLocationDto dto) {
        try {
            VehicleDto vehicleDto = vehicleService.findById(dto.vehicleId());
            repository.save(mapper.dtoToEntity(dto, vehicleDto));
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage() + " %s ".formatted(dto.vehicleId()));
        } catch (InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void saveAsync(RequestTrackingLocationDto dto) {
        producer.send(dto);
    }
}
