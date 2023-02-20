package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.SensorDto;
import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.dto.sensor.ResponseTrackingSensorDto;
import br.com.raynerweb.tracking.entity.TrackingSensorEntity;
import br.com.raynerweb.tracking.exception.InternalServerErrorException;
import br.com.raynerweb.tracking.exception.NotFoundException;
import br.com.raynerweb.tracking.mapper.TrackingSensorMapper;
import br.com.raynerweb.tracking.producer.TrackingLocationProducer;
import br.com.raynerweb.tracking.producer.TrackingSensorProducer;
import br.com.raynerweb.tracking.repository.TrackingSensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrackingSensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingSensorService.class);

    @Autowired
    private TrackingSensorRepository repository;

    @Autowired
    private TrackingSensorProducer producer;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private TrackingSensorMapper mapper;

    public List<ResponseTrackingSensorDto> findAll() {
        return repository.findAll().stream().map(mapper::entityToDto).toList();
    }

    public void saveAsync(RequestTrackingSensorDto dto) {
        producer.send(dto);
    }

    public void save(RequestTrackingSensorDto dto) {
        try {
            VehicleDto vehicleDto = vehicleService.findById(dto.vehicleId());
            SensorDto sensorDto = sensorService.findById(dto.sensorId());
            repository.save(mapper.dtoToEntity(dto, vehicleDto, sensorDto));
        } catch (NotFoundException e) {
            LOGGER.error(e.getMessage() + " %s ".formatted(dto.vehicleId()));
        } catch (InternalServerErrorException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
