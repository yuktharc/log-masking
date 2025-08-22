package com.logger.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class StructuredLogger {

    private static final Logger logger = LoggerFactory.getLogger(StructuredLogger.class);

    public void logInfo(String message, Object... context) {
        log("INFO", message, context);
    }

    public void logDebug(String message, Object... context) {
        log("DEBUG", message, context);
    }

    public void logError(String message, Throwable throwable, Object... context) {
        log("ERROR", message, context);
        logger.error(message, throwable);
    }

    private void log(String level, String message, Object... context) {
        // Add context to MDC
        for (Object ctx : context) {
            if (ctx instanceof LogContext) {
                LogContext logContext = (LogContext) ctx;
                MDC.put("requestId", logContext.getRequestId());
                MDC.put("userId", logContext.getUserId());
                // Add more context as needed
            }
        }

        switch (level) {
            case "INFO":
                logger.info(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            case "ERROR":
                logger.error(message);
                break;
            default:
                logger.info(message);
                break;
        }

        // Clear MDC after logging
        MDC.clear();
    }
}