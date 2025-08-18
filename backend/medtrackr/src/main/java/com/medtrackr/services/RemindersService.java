package com.medtrackr.medtrackr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RemindersService {

    @Autowired
    private SimpMessagingTemplate template;

    // This will send a message to /topic/reminders, if client is subscribed to /topic/reminders/, it will receive it
    public void sendReminder(String message) {
        template.convertAndSend("/topic/reminders", message);
    }

    // if we had messagemapping(/reminders) it handles incoming STOMP messages, this will map the message to /app/reminders.
}
