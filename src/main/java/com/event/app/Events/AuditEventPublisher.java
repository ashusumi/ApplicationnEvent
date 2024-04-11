package com.event.app.Events;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.event.app.model.LogData;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuditEventPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishEvent(String message) {
		Map<String, String> datamap = new LinkedHashMap<>();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		datamap.put("host", request.getHeader("host"));
		datamap.put("user-agent", request.getHeader("User-Agent"));
		datamap.put("message", message);
		applicationEventPublisher.publishEvent(new AuditEvent<LogData>(LogData.builder().data(datamap).build()));

	}
}
