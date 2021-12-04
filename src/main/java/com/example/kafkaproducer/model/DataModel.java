package com.example.kafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author RinkeshKM
 * @project KafkaProducer
 * @created 03/12/2021 - 09:54 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataModel {
    @JsonProperty
    private String rightsId;
    @JsonProperty
    private String errorCode;
    @JsonProperty
    private Integer dueSettled;
    @JsonProperty
    private String lotId;
    @JsonProperty
    private Double settlementAmount;
    @JsonProperty
    private Double amountCollected;
}
