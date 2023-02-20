package br.com.raynerweb.tracking.consumer;

import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.service.TrackingLocationService;
import br.com.raynerweb.tracking.service.TrackingSensorService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TrackingSensorConsumer {

    @Autowired
    private TrackingSensorService service;

    @Autowired
    private Gson gson;

    @RabbitListener(queues = {"${br.com.raynerweb.sensors.queue}"})
    public void consume(@Payload String payload) {
        service.save(gson.fromJson(payload, RequestTrackingSensorDto.class));
    }

}
