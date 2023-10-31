package com.azry.LMS.service.impl;

import com.azry.LMS.model.BookEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookEventPublisher {

    @Autowired
    private  JmsTemplate jmsTemplate;

    public void publishBookEvent(BookEvent bookEvent) {
        jmsTemplate.convertAndSend("bookEventQueue", bookEvent);
    }
}