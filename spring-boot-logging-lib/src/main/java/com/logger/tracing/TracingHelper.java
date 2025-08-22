package com.logger.tracing;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;

public class TracingHelper {

    private static final Tracer tracer = GlobalOpenTelemetry.getTracer("com.logger");

    public static Span startSpan(String spanName) {
        return tracer.spanBuilder(spanName)
                .setSpanKind(SpanKind.INTERNAL)
                .startSpan();
    }

    public static void endSpan(Span span) {
        if (span != null) {
            span.end();
        }
    }

    public static void addAttribute(Span span, String key, String value) {
        if (span != null) {
            span.setAttribute(key, value);
        }
    }
}