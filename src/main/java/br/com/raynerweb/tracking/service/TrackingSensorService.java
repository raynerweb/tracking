package br.com.raynerweb.tracking.service;

import br.com.raynerweb.tracking.dto.TrackingSensorDto;
import br.com.raynerweb.tracking.repository.TrackingSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingSensorService {

    @Autowired
    private TrackingSensorRepository repository;

    public void save(TrackingSensorDto dto) {

    }

}
