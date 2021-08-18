package com.example.banking.event;

import java.util.UUID;

public abstract class DomainEvent {
	private final String eventId;
	private final long timestamp;

	public DomainEvent() {
		eventId = UUID.randomUUID().toString();
		timestamp = UUID.randomUUID().timestamp();
	}

	public String getEventId() {
		return eventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
