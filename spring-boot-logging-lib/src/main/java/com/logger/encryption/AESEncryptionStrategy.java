public class AESEncryptionStrategy implements EncryptionStrategy {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    @Override
    public String encrypt(String data, String key) throws Exception {
        // Implement AES encryption logic here
        // Use the provided key to encrypt the data
    }

    @Override
    public String decrypt(String encryptedData, String key) throws Exception {
        // Implement AES decryption logic here
        // Use the provided key to decrypt the data
    }
}