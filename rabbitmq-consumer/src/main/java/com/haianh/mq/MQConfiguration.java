package com.haianh.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfiguration {
    public static final String QUEUE_MESSAGE_A = "message_queue_a";
    public static final String QUEUE_MESSAGE_B = "message_queue_b";
    public static final String MESSAGE_EXCHANGE = "fanout_exchange";

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_MESSAGE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_MESSAGE_B);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding bindingA(Queue queueA, FanoutExchange exchange) {
        return BindingBuilder
                .bind(queueA)
                .to(exchange);
    }

    @Bean
    public Binding bindingB(Queue queueB, FanoutExchange exchange) {
        return BindingBuilder
                .bind(queueB)
                .to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
