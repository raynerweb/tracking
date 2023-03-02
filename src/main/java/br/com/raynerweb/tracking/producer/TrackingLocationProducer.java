package br.com.raynerweb.tracking.producer;

import br.com.raynerweb.tracking.dto.location.RequestTrackingLocationDto;
import com.google.gson.Gson;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static br.com.raynerweb.tracking.config.RabbitMQConfig.LOCATION_QUEUE;

@Component
public class TrackingLocationProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier(LOCATION_QUEUE)
    private Queue queue;

    @Autowired
    private Gson gson;

    public void send(RequestTrackingLocationDto dto) {
        rabbitTemplate.convertAndSend(this.queue.getName(), gson.toJson(dto));
    }
}
