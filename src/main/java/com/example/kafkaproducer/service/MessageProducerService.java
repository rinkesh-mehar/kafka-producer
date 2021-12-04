package com.example.kafkaproducer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RinkeshKM
 * @project KafkaProducer
 * @created 04/12/2021 - 01:12 PM
 */

@Service
public class MessageProducerService {

    Logger logger = LoggerFactory.getLogger(MessageProducerService.class);

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

//    private static final String TOPIC = "right_information";
    private static final String TOPIC = "KrishiRigthsOnBoarding";

    public Map<String, Object> publishToQueue(String key, Map<String, Object> value) {
        Map<String, Object> responseMap = new HashMap<>();

        ProducerRecord<String, Map<String, Object>> producerRecord = this.buildProducerRecord(key, value, TOPIC);
        ListenableFuture<SendResult<String, Map<String, Object>>> listenableFuture = kafkaTemplate.send(producerRecord);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<String, Map<String, Object>> result) {
                handleSuccess(key, value, result);
            }
        });
        responseMap.put("message", "Message Sent SuccessFully for the key : "+ key +" and the value is "+value);
        responseMap.put("success", true);
        responseMap.put("error", "");
        return responseMap;
    }

    private ProducerRecord<String, Map<String, Object>> buildProducerRecord(String key, Map<String, Object> value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
        return new ProducerRecord(topic, null, key, value, recordHeaders);
    }

    private void handleFailure(String key, Map<String, Object> value, Throwable ex) {
        logger.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            logger.error("Error On Failure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(String key, Map<String, Object> value, SendResult<String, Map<String, Object>> result) {
        logger.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }

}
