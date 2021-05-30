/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriales.rabbitmq.spring.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoriales.rabbitmq.spring.RabbitMqConfig;

/**
 *
 * @author ricardo
 */
@RestController
@RequestMapping("/cliente1")
public class controller {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/{mensaje}")
    public ResponseEntity<?> enviarMensaje(@PathVariable("mensaje") String mensaje) {
         rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, "Cliente 1 envio "+mensaje);
        return ResponseEntity.ok(mensaje);
    }

}
