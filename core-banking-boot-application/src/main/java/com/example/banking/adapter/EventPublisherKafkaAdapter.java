package com.example.banking.adapter;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.banking.event.DomainEvent;
import com.example.banking.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher {
	private KafkaTemplate<String, String> kafkaTemplate;
	private ObjectMapper objectMapper;

	public EventPublisherKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publish(DomainEvent event) {
		try {
			kafkaTemplate.send("account-events", objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to Json: " + e.getMessage());
		}
	}

}
