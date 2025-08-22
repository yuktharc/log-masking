import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Primary;

@Configuration
@AutoConfigureAfter(ConditionEvaluationReportLoggingListener.class)
public class LoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public StructuredLogger structuredLogger() {
        return new StructuredLogger();
    }

    @Bean
    @Primary
    public LogContext logContext() {
        return new LogContext();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogBuilder logBuilder() {
        return new LogBuilder(logContext());
    }

    // Additional beans for request/response logging, masking, and encryption can be added here
}