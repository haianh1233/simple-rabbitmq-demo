package com.haianh.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    private final RabbitTemplate template;

    public MessagePublisher(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/publish")
    public String publishMessageA(@RequestHeader("colour") String colour, @RequestBody CustomMessage customMessage) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("colour", colour);
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());

        MessageConverter converter = new SimpleMessageConverter();
        Message message = converter.toMessage(customMessage, messageProperties);

        template.convertAndSend(MQConfiguration.MESSAGE_EXCHANGE,
                "",
                message);

        return "Message Published";
    }

}
