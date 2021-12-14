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
    public static final String QUEUE_MESSAGE_A = "message.a";
    public static final String QUEUE_MESSAGE_B = "message.b";
    public static final String MESSAGE_EXCHANGE = "header.exchange";

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_MESSAGE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_MESSAGE_B);
    }

    @Bean
    public HeadersExchange exchange() {
        return new HeadersExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding bindingA(Queue queueA, HeadersExchange exchange) {
        return BindingBuilder
                .bind(queueA)
                .to(exchange)
                .where("colour")
                .matches("black");
    }

    @Bean
    public Binding bindingB(Queue queueB, HeadersExchange exchange) {
        return BindingBuilder
                .bind(queueB)
                .to(exchange)
                .where("colour")
                .matches("green");
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
