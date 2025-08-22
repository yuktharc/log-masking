# log-masking
## Objectives

1. Structured JSON logging (with context)
2. Request/response logging with configurable masking
3. Custom log messages with masking + encryption
4. Distributed tracing integration (OpenTelemetry)
5. Extendable masking/redaction/encryption strategies
6. MDC enrichment via Spring filters/interceptors
7. Standardized Logging API
8. PII/secret auto-detection & warnings
9. Log context snapshotting (dump MDC + request context in one structured log event)

## Common Developer Log Statement Formats

Developers typically write logs in three main styles:

A. Simple message logs
log.info("User created successfully");
log.error("Failed to process request");


No structured data, just a message.

Often misses request context, IDs, or correlation info.

B. Message + concatenated variables
log.info("User created: " + userId + ", email: " + email);
log.error("Payment failed for user " + userId + ": " + ex.getMessage());


Variables are concatenated into a single string.

Sensitive information may be included directly.

Hard to parse automatically in logging systems (ELK, Splunk).

C. Multiple parameters or key/value pairs
log.info("User created successfully", userId, email);
log.debug("Processing order", Map.of("orderId", orderId, "amount", amount));


Slightly better, sometimes still unstructured.

Often developers don’t mask sensitive info (PII).

2. Converting to Structured JSON Logs

Goal: Every log should include:

@timestamp – ISO8601 timestamp

level – INFO, DEBUG, ERROR

logger_name – Class or component

message – Human-readable description

fields – Masked key/value data (IDs, emails, SSNs)

correlationId – Request-level ID

traceId / spanId – Distributed tracing identifiers

service / environment – Optional service metadata

A. Simple message → structured

Before:

log.info("User created successfully");


After:

{
  "@timestamp": "2025-08-21T23:55:12.123Z",
  "level": "INFO",
  "logger_name": "UserService",
  "message": "User created successfully",
  "correlationId": "abc123"
}

B. Message + concatenated variables → structured

Before:

log.info("User created: " + userId + ", email: " + email);


After:

{
  "@timestamp": "2025-08-21T23:56:12.123Z",
  "level": "INFO",
  "logger_name": "UserService",
  "message": "User created",
  "fields": {
    "userId": "12345",
    "email": "****"
  },
  "correlationId": "abc123"
}


Key changes:

Split message and fields.

Mask sensitive values.

C. Map or multi-variable logging → structured

Before:

log.debug("Processing order", Map.of("orderId", orderId, "amount", amount));


After:

{
  "@timestamp": "2025-08-21T23:57:12.123Z",
  "level": "DEBUG",
  "logger_name": "OrderService",
  "message": "Processing order",
  "fields": {
    "orderId": "98765",
    "amount": 250.75
  },
  "correlationId": "xyz789"
}


Already closer to structured; just need masking and standard field placement.

# Example Conversion Table
Original Log	Converted Structured Log
log.info("Payment failed")	{ "message": "Payment failed", "level": "INFO", "fields": {}, "correlationId": "abc123" }
log.info("User created: " + userId + ", email: " + email)	{ "message": "User created", "fields": { "userId": "12345", "email": "****" } }
log.debug("Processing order", Map.of("orderId", orderId, "amount", amount))	{ "message": "Processing order", "fields": { "orderId": "98765", "amount": 250.75 } }