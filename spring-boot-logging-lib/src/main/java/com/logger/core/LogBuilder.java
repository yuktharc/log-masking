package com.logger.core;

import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public class LogBuilder {
    private String message;
    private Map<String, Object> context = new HashMap<>();

    public LogBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public LogBuilder withContext(String key, Object value) {
        context.put(key, value);
        return this;
    }

    public LogBuilder withMDC(String key, String value) {
        MDC.put(key, value);
        return this;
    }

    public StructuredLog build() {
        return new StructuredLog(message, context);
    }
}