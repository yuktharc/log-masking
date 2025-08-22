# Spring Boot Logging Library

This project is a Spring Boot library designed to provide structured JSON logging with context, request/response logging with configurable masking, and integration with distributed tracing using OpenTelemetry. It aims to offer a developer-friendly and standardized logging API while ensuring the security of sensitive information through masking and encryption strategies.

## Features

1. **Structured JSON Logging**: Implements structured logging to enhance log readability and context.
2. **Request/Response Logging**: Logs incoming requests and outgoing responses with configurable masking options for sensitive data.
3. **Custom Log Messages**: Supports custom log messages with built-in masking and encryption capabilities.
4. **Distributed Tracing Integration**: Integrates with OpenTelemetry for distributed tracing, allowing for better observability of microservices.
5. **Extendable Masking/Redaction/Encryption Strategies**: Provides interfaces for custom implementations of masking, redaction, and encryption strategies.
6. **MDC Enrichment**: Enriches the Mapped Diagnostic Context (MDC) with additional request information for improved logging context.
7. **Standardized Logging API**: Offers a consistent logging interface that covers various log statement patterns.
8. **PII/Secret Auto-Detection**: Automatically detects Personally Identifiable Information (PII) and provides warnings in the logs.
9. **Log Context Snapshotting**: Allows for capturing a snapshot of the logging context, including MDC and request context, in a structured log event.

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle 6.0 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd spring-boot-logging-lib
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Include the library in your Spring Boot application by adding the following dependency to your `build.gradle`:
   ```groovy
   implementation 'com.logger:spring-boot-logging-lib:1.0.0'
   ```

### Usage

To use the logging library, simply autowire the `StructuredLogger` in your Spring components and start logging with the provided methods.

### Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.