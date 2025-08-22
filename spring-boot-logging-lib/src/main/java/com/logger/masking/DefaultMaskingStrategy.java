public class DefaultMaskingStrategy implements MaskingStrategy {

    @Override
    public String mask(String input) {
        // Implement default masking logic here
        // For example, replace sensitive information with asterisks
        if (input == null) {
            return null;
        }
        return input.replaceAll("(?<=.{2}).(?=.*@)", "*"); // Masking email addresses
    }
}