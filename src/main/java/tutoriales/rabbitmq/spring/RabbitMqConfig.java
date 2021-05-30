package tutoriales.rabbitmq.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE_NAME = "topic-cliente1";
    public static final String ROUTING_KEY = "routing_key";

    private static final String QUEUE_NAME = "queue_cliente1";
    private static final boolean IS_DURABLE_QUEUE = false;

    
//    @Value("my.queue.names") private List<String> queueNames;
//
//    @Bean
//    public Declarables declarables() {
//        return new Declarables(
//            new DirectExchange("exchangeName", false, true),
//            new Queue("queueName", false, false, true),
//            new Binding("destination", DestinationType.QUEUE, "exchange", "routingKey", null));
//    }
//    
//    @Bean
//    public Declarables queueDeclarable() {
//        Declarables declarables = new Declarables();
//        declarables.addAll(queueNames.stream().map(queueName -> new Queue(queueName, false, false, true)).collect(Collectors.toList());
//        return declarables;
//    }
    
    
    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, IS_DURABLE_QUEUE);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
/*
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, Receiver.RECEIVE_METHOD_NAME);
    }
*/
    
    
//    @RabbitListener(queues = QUEUE_NAME)
//    public void receiveMessage(String message) {
//        System.out.println("[Receiver] ha recibido el mensaje \"" + message + '"');
//    }

}
