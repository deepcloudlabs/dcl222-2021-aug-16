package com.example.banking.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.banking.event.DomainEvent;
import com.example.banking.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Object Adapter vs Class Adapter
// Strategy: RabbitMQ vs Kafka?
@Service
@ConditionalOnProperty(name = "messagingStrategy", havingValue = "rabbit")
public class EventPublisherRabbitAdapter implements EventPublisher {
	private RabbitTemplate rabbitTemplate;
	private ObjectMapper objectMapper;

	public EventPublisherRabbitAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publish(DomainEvent event) {
		try {
			rabbitTemplate.convertAndSend("bankingex", null, objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to Json: " + e.getMessage());
		}
	}

}
