package br.com.raynerweb.tracking.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String SENSO_QUEUE = "sensorQueue";
    public static final String LOCATION_QUEUE = "locationQueue";

    @Value("${br.com.raynerweb.sensors.queue}")
    private String sensorQueueName;

    @Value("${br.com.raynerweb.location.queue}")
    private String locationQueueName;


    @Bean(name = SENSO_QUEUE)
    public Queue sensorQueue() {
        return new Queue(sensorQueueName, false);
    }

    @Bean(name = LOCATION_QUEUE)
    public Queue locationQueue() {
        return new Queue(locationQueueName, false);
    }
}
