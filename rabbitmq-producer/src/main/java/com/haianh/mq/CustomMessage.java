package com.haianh.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage {
    private String messageId;
    private String message;
    private Date messageDate;
}
