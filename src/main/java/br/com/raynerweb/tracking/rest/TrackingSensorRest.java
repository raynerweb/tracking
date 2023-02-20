package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.TrackingSensorDto;
import br.com.raynerweb.tracking.service.TrackingSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tracking-sensor")
public class TrackingSensorRest {

    @Autowired
    private TrackingSensorService service;

    @PostMapping
    public void save(TrackingSensorDto dto) {
        service.save(dto);
    }
}
