package com.mfinancials.security.user.service;

import com.google.gson.Gson;
import com.mfinancials.security.user.model.UserDetailsImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;
    private final static Gson GSON = new Gson();

    @Autowired
    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void userRegistered(UserDetailsImpl userDetails) {
        rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", GSON.toJson(userDetails));
    }
}
