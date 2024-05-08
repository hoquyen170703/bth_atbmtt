package ceasar;

public class CaesarCipher {

    // Hàm mã hoá Caesar Cipher
    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toLowerCase(); // Chuyển đổi văn bản thành chữ thường

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                char shiftedChar = (char) ('a' + (ch - 'a' + shift) % 26); // Áp dụng phép dịch
                ciphertext.append(shiftedChar);
            } else {
                ciphertext.append(ch); // Giữ nguyên các ký tự không phải là chữ cái
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toLowerCase(); // Chuyển đổi văn bản mã hoá thành chữ thường

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isLetter(ch)) {
                char shiftedChar = (char) ('a' + (ch - 'a' - shift + 26) % 26); // Áp dụng phép dịch ngược
                plaintext.append(shiftedChar);
            } else {
                plaintext.append(ch); // Giữ nguyên các ký tự không phải là chữ cái
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Chao mung ban den voi abc";
        int shift = 3; // Số lượng ký tự dịch chuyển
        String encryptedText = encrypt(plaintext, shift);
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}