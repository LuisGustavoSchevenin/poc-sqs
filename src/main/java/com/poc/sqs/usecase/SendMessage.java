package com.poc.sqs.usecase;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.sqs.config.SQSConfig;
import com.poc.sqs.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SendMessage {

    private static final Logger LOG = LoggerFactory.getLogger(SendMessage.class);

    @Resource
    private SQSConfig sqsConfig;


    public void sendMessage(Person person) {
        String messageContent = buildMessage();

        if (person != null) {
            messageContent = buildsMessage(person);
        }

        AmazonSQS amazonSQS = sqsConfig.getAmazonSQSClient();

        SendMessageRequest sendMessageRequest = new SendMessageRequest(sqsConfig.getAmazonSQSQueue(), messageContent);
        sendMessageRequest.setMessageGroupId("grupo1");

        SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);

        LOG.info("Message sent with success");
        LOG.info("Sequence Number [{}]", sendMessageResult.getSequenceNumber());
        LOG.info("Message ID [{}]", sendMessageResult.getMessageId());
    }


    private String buildMessage() {
        return "Message without attributes.";
    }

    private String buildsMessage(Person person) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            LOG.warn("Error trying to convert a Person object to JSON", e);
        }

        return "The attributes couldn't be filled";
    }
}
