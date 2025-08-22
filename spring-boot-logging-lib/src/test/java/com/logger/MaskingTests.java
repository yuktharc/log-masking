Sure, here's the contents for the file `/spring-boot-logging-lib/spring-boot-logging-lib/src/test/java/com/logger/MaskingTests.java`:

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.logger.masking.DefaultMaskingStrategy;
import com.logger.masking.MaskingStrategy;
import com.logger.masking.PIIDetector;

public class MaskingTests {

    private MaskingStrategy maskingStrategy;
    private PIIDetector piiDetector;

    @BeforeEach
    public void setUp() {
        maskingStrategy = new DefaultMaskingStrategy();
        piiDetector = new PIIDetector();
    }

    @Test
    public void testMaskingSensitiveData() {
        String sensitiveData = "My credit card number is 1234-5678-9012-3456";
        String maskedData = maskingStrategy.mask(sensitiveData);
        
        assertTrue(maskedData.contains("****-****-****-3456"));
    }

    @Test
    public void testPIIDetection() {
        String logMessage = "User email is user@example.com";
        boolean containsPII = piiDetector.containsPII(logMessage);
        
        assertTrue(containsPII);
    }

    @Test
    public void testMaskingWithCustomPattern() {
        String customSensitiveData = "My SSN is 123-45-6789";
        String maskedData = maskingStrategy.mask(customSensitiveData);
        
        assertEquals("My SSN is ***-**-6789", maskedData);
    }
}