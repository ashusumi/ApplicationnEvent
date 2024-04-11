package com.event.app.model;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogData {

	private Map<String, String> data;

}
