package com.poc.sqs.controller;

import com.poc.sqs.entity.PersonData;
import com.poc.sqs.usecase.RetrieveMessage;
import com.poc.sqs.usecase.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/queue")
public class QueueController {

    private static final Logger LOG = LoggerFactory.getLogger(QueueController.class);

    @Resource
    private SendMessage sendMessage;
    @Resource
    private RetrieveMessage retrieveMessage;


    @PostMapping(value = "/sendMessage")
    public ResponseEntity<HttpStatus> sendMessage(@RequestBody PersonData personData) {
        LOG.info("Sending a message to the queue");
        sendMessage.sendMessage(personData.toDomain());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/retrieveMessages")
    public ResponseEntity<PersonData> getMessages() {
        LOG.info("Retrieving messages from the queue");
        retrieveMessage.retrieveMessages();
        PersonData personData = null; //TODO finalize the implementation of the method + Google Check Style
        return ResponseEntity.ok(personData);
    }

}
