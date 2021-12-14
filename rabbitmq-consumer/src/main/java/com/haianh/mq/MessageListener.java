package com.haianh.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfiguration.QUEUE_MESSAGE_A)
    public void listenerA(CustomMessage message) {
        System.out.println(MQConfiguration.QUEUE_MESSAGE_A + "->" +message);
    }

    @RabbitListener(queues = MQConfiguration.QUEUE_MESSAGE_B)
    public void listenerB(CustomMessage message) {
        System.out.println(MQConfiguration.QUEUE_MESSAGE_B + "->" + message);
    }
}
