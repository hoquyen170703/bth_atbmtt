package ceasar;

public class CaesarCipher {

    // Bảng chữ cái tiếng Việt
    private static final String VIETNAMESE_ALPHABET = "aáàảãạăắằẳẵặâấầẩẫậbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvwxyýỳỷỹỵz ";

    // Hàm mã hóa văn bản tiếng Việt bằng thuật toán Caesar
    public static String encrypt(String plaintext, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        // Chuyển đổi vị trí về phạm vi dương
        shift = shift % VIETNAMESE_ALPHABET.length();
        if (shift < 0) {
            shift += VIETNAMESE_ALPHABET.length();
        }

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            int index = VIETNAMESE_ALPHABET.indexOf(currentChar);
            if (index != -1) {
                int encryptedIndex = (index + shift) % VIETNAMESE_ALPHABET.length();
                encryptedText.append(VIETNAMESE_ALPHABET.charAt(encryptedIndex));
            } else {
                // Giữ nguyên ký tự không thuộc bảng chữ cái tiếng Việt
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    // Hàm giải mã văn bản tiếng Việt bằng thuật toán Caesar
    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, -shift);
    }

    public static void main(String[] args) {
        // Văn bản cần mã hóa và giải mã
        String plaintext = "xin chào, đây là một ví dụ về mã hóa và giải mã Caesar";

        // Số lượng dịch chuyển
        int shift = 3;

        // Mã hóa văn bản
        String encryptedText = encrypt(plaintext, shift);
        System.out.println("Văn bản đã mã hóa: " + encryptedText);

        // Giải mã văn bản
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Văn bản đã giải mã: " + decryptedText);
    }
}
