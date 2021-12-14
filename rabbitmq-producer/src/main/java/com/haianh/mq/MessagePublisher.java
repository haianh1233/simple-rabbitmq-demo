package com.haianh.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    private final RabbitTemplate template;

    public MessagePublisher(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/publishA")
    public String publishMessageA(@RequestBody CustomMessage customMessage) {
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        template.convertAndSend(MQConfiguration.MESSAGE_EXCHANGE,
                MQConfiguration.ROUTING_A,
                customMessage);

        return "Message A Published";
    }

    @PostMapping("/publishB")
    public String publishMessageB(@RequestBody CustomMessage customMessage) {
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        template.convertAndSend(MQConfiguration.MESSAGE_EXCHANGE,
                MQConfiguration.ROUTING_B,
                customMessage);

        return "Message B Published";
    }

}
