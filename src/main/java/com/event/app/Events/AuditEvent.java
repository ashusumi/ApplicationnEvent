package com.event.app.Events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class AuditEvent<T> extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T data;

	public AuditEvent(T event) {
		super(event);

	}

}
