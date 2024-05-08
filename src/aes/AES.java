package aes;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final int AES_KEY_SIZE = 128; // Kích thước của khóa AES (128 bits)

    public static byte[] encrypt(byte[] input, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    public static byte[] decrypt(byte[] encrypted, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(encrypted);
    }

    public static void main(String[] args) throws Exception {
        String originalText = "Hello, AES!";
        System.out.println("Original: " + originalText);

        // Tạo một khóa ngẫu nhiên
        byte[] key = generateAESKey();

        // Mã hóa văn bản gốc
        byte[] encryptedText = encrypt(originalText.getBytes(), key);
        System.out.println("Encrypted: " + new String(encryptedText));

        // Giải mã văn bản đã mã hóa
        byte[] decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + new String(decryptedText));
    }

    private static byte[] generateAESKey() {
        byte[] key = new byte[AES_KEY_SIZE / 8]; // Kích thước khóa trong bytes
        new SecureRandom().nextBytes(key);
        return key;
    }
}
