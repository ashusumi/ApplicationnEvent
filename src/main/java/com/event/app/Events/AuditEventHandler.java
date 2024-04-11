package com.event.app.Events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.event.app.model.LogData;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class AuditEventHandler {

	@SneakyThrows
	@EventListener
	@Async
	public void handleEvent(AuditEvent<LogData> auditEvent) {
		System.out
				.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(auditEvent.getData()));
	}
}
