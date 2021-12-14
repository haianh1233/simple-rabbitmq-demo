package com.haianh.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfiguration.QUEUE_MESSAGE_A)
    public void listenerA(Message message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
        ObjectInput in = new ObjectInputStream(bis);
        CustomMessage customMessage = (CustomMessage) in.readObject();
        System.out.println(MQConfiguration.QUEUE_MESSAGE_A + "->" + customMessage);
    }

    @RabbitListener(queues = MQConfiguration.QUEUE_MESSAGE_B)
    public void listenerB(Message message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
        ObjectInput in = new ObjectInputStream(bis);
        CustomMessage customMessage = (CustomMessage) in.readObject();

        System.out.println(MQConfiguration.QUEUE_MESSAGE_B + "->" + customMessage);
    }

}
