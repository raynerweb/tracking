package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.TrackingLocationDto;
import br.com.raynerweb.tracking.service.TrackingLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tracking-location")
public class TrackingLocationRest {
    @Autowired
    private TrackingLocationService service;

    @PostMapping
    public void save(TrackingLocationDto dto) {
        service.save(dto);
    }
}
