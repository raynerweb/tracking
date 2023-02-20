package br.com.raynerweb.tracking.consumer;

import br.com.raynerweb.tracking.dto.TrackingLocationDto;
import br.com.raynerweb.tracking.service.TrackingLocationService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TrackingLocationConsumer {

    @Autowired
    private TrackingLocationService service;

    @Autowired
    private Gson gson;

    @RabbitListener(queues = {"${br.com.raynerweb.location.queue}"})
    public void consume(@Payload String payload) {
        service.save(gson.fromJson(payload, TrackingLocationDto.class));
    }
}
