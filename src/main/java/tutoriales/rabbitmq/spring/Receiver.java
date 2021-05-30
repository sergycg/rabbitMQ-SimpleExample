package tutoriales.rabbitmq.spring;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Receiver {

    public static final String RECEIVE_METHOD_NAME = "receiveMessage";
    private static final String QUEUE_NAME = "queue_cliente1";

    @RabbitListener(queues = {QUEUE_NAME})
    public void receiveMessage(String message) {
        System.out.println("[Receiver] ha recibido el mensaje \"" + message + '"');
    }
}