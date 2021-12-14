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
    public static final String QUEUE_MESSAGE_ALL = "message.all";
    public static final String MESSAGE_EXCHANGE = "topic_exchange";
    public static final String ROUTING_A = "routing.a";
    public static final String ROUTING_B = "routing.b";
    public static final String ROUTING_ALL = "routing.*";

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_MESSAGE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_MESSAGE_B);
    }

    @Bean
    public Queue allQueue() {
        return new Queue(QUEUE_MESSAGE_ALL);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding bindingA(Queue queueA, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueA)
                .to(exchange)
                .with(ROUTING_A);
    }

    @Bean
    public Binding bindingB(Queue queueB, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueB)
                .to(exchange)
                .with(ROUTING_B);
    }

    @Bean
    public Binding bindingAll(Queue allQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(allQueue)
                .to(exchange)
                .with(ROUTING_ALL);
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
