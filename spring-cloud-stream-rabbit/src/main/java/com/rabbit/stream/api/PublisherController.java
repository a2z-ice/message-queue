package com.rabbit.stream.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

//    @Qualifier("output")
    private final MessageChannel output;

    public PublisherController(MessageChannel output) {
        this.output = output;
    }

    @PostMapping("/publish")
    public Book publishEvent(@RequestBody Book book) {
        output.send(MessageBuilder.withPayload(book).build());
        return book;
    }
}
