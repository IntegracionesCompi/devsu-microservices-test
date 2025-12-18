package com.devsu.cuenta.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String MOVIMIENTOS_EXCHANGE = "movimientos.exchange";
    public static final String MOVIMIENTO_REGISTRADO_QUEUE = "movimiento.registrado.queue";
    public static final String MOVIMIENTO_REGISTRADO_KEY = "movimiento.registrado";

    @Bean
    public DirectExchange movimientosExchange() {
        return new DirectExchange(MOVIMIENTOS_EXCHANGE, true, false);
    }

    @Bean
    public Queue movimientoRegistradoQueue() {
        return new Queue(MOVIMIENTO_REGISTRADO_QUEUE, true);
    }

    @Bean
    public Binding movimientoRegistradoBinding(Queue movimientoRegistradoQueue, DirectExchange movimientosExchange) {
        return BindingBuilder
                .bind(movimientoRegistradoQueue)
                .to(movimientosExchange)
                .with(MOVIMIENTO_REGISTRADO_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
