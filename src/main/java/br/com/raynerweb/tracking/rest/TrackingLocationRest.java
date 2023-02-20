package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.service.TrackingLocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tracking-location")
public class TrackingLocationRest {
    @Autowired
    private TrackingLocationService service;

    @GetMapping
    public List<ResponseTrackingLocationDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(@Valid
                     @RequestBody RequestTrackingLocationDto dto) {
        service.saveAsync(dto);
    }
}
