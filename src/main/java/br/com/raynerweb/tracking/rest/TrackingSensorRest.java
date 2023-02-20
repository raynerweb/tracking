package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.dto.sensor.ResponseTrackingSensorDto;
import br.com.raynerweb.tracking.service.TrackingSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tracking-sensor")
public class TrackingSensorRest {

    @Autowired
    private TrackingSensorService service;

    @GetMapping
    public List<ResponseTrackingSensorDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(@RequestBody RequestTrackingSensorDto dto) {
        service.saveAsync(dto);
    }
}
