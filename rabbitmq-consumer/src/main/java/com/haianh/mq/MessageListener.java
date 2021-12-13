package com.haianh.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfiguration.QUEUE_MESSAGE)
    public void listener(CustomMessage message) {
        System.out.println(message);
    }
}
