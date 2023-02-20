package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.TrackingLocationDto;
import br.com.raynerweb.tracking.dto.VehicleDto;
import br.com.raynerweb.tracking.producer.TrackingLocationProducer;
import br.com.raynerweb.tracking.repository.TrackingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingLocationService {

    @Autowired
    private TrackingLocationRepository repository;

    @Autowired
    private TrackingLocationProducer producer;

    @Autowired
    private VehicleService vehicleService;

    public void save(TrackingLocationDto dto) {
        VehicleDto vehicleDto = vehicleService.findById(dto.vehicleId());

    }

    public void saveAsync(TrackingLocationDto dto) {
        producer.send(dto);
    }
}
