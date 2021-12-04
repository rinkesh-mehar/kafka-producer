package com.example.kafkaproducer.service;

import com.example.kafkaproducer.model.DataModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author RinkeshKM
 * @project KafkaProducer
 * @created 04/12/2021 - 01:15 PM
 */

public class MessageSerializer implements Serializer<Map<String, Object>> {

    @Override
    public byte[] serialize(String topic, Map<String, Object> data) {
        byte[] serializedValue = null;
        ObjectMapper om = new ObjectMapper();
        if(data != null) {
            try {
                serializedValue = om.writeValueAsString(data).getBytes();
            } catch (JsonProcessingException e) {
            }
        }
        return serializedValue;
    }


}
