public interface EncryptionStrategy {
    String encrypt(String data);
    String decrypt(String encryptedData);
}