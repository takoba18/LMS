package com.azry.LMS.service.impl;

import com.azry.LMS.model.BookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookEventListener.class);

    @JmsListener(destination = "bookEventQueue")
    public void receiveBookEvent(BookEvent bookEvent) {
        LOGGER.info("Received message: {}", bookEvent);

        if ("BORROW".equals(bookEvent.getAction())) {
            LOGGER.info("Book borrowed: {}", bookEvent.getBookId());
        } else if ("RETURN".equals(bookEvent.getAction())) {
            LOGGER.info("Book returned: {}", bookEvent.getBookId());
        } else {
            LOGGER.info("Unknown book event: {}", bookEvent);
        }
    }
}




