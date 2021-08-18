package com.example.banking.infrastructure;

import com.example.banking.event.DomainEvent;

public interface EventPublisher {

	void publish(DomainEvent event);
	
}
