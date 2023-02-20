package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.TrackingLocationDto;
import br.com.raynerweb.tracking.repository.TrackingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingLocationService {

    @Autowired
    private TrackingLocationRepository repository;

    public void save(TrackingLocationDto dto) {

    }
}
