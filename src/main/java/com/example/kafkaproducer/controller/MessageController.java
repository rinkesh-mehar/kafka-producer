package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.model.DataModel;
import com.example.kafkaproducer.service.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RinkeshKM
 * @project KafkaProducer
 * @created 04/12/2021 - 11:00 AM
 */

@RestController
public class MessageController {

    @Autowired
    private MessageProducerService messageProducer;

    @PostMapping("/publish-right")
    public ResponseEntity<?> publishMessages(@RequestBody DataModel dataModel) {
        Map<String, Object> publishMap = new HashMap<>();
        publishMap.put("data", dataModel);
        Map<String, Object> response = messageProducer.publishToQueue("cdt-key", publishMap);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
