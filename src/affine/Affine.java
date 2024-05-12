package affine;

public class Affine {

    // Bảng chữ cái tiếng Việt
    private static final String VIETNAMESE_ALPHABET = "aáàảãạăắằẳẵặâấầẩẫậbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvwxyýỳỷỹỵz ";

    // Hằng số a trong hệ mã affine (a và b phải là số nguyên tố cùng nhau với độ dài của bảng chữ cái)
    private static final int a = 5;

    // Hằng số b trong hệ mã affine
    private static final int b = 7;

    // Hàm mã hóa văn bản tiếng Việt bằng hệ mã affine
    public static String encrypt(String plaintext) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            int index = VIETNAMESE_ALPHABET.indexOf(currentChar);
            if (index != -1) {
                int encryptedIndex = (a * index + b) % VIETNAMESE_ALPHABET.length();
                encryptedText.append(VIETNAMESE_ALPHABET.charAt(encryptedIndex));
            } else {
                // Giữ nguyên ký tự không thuộc bảng chữ cái tiếng Việt
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    // Hàm giải mã văn bản tiếng Việt bằng hệ mã affine
    public static String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();

        // Tìm phần tử nghịch đảo của a
        int inverseA = -1;
        for (int i = 1; i < VIETNAMESE_ALPHABET.length(); i++) {
            if ((a * i) % VIETNAMESE_ALPHABET.length() == 1) {
                inverseA = i;
                break;
            }
        }

        if (inverseA != -1) {
            for (int i = 0; i < ciphertext.length(); i++) {
                char currentChar = ciphertext.charAt(i);
                int index = VIETNAMESE_ALPHABET.indexOf(currentChar);
                if (index != -1) {
                    int decryptedIndex = (inverseA * (index - b + VIETNAMESE_ALPHABET.length())) % VIETNAMESE_ALPHABET.length();
                    decryptedText.append(VIETNAMESE_ALPHABET.charAt(decryptedIndex));
                } else {
                    // Giữ nguyên ký tự không thuộc bảng chữ cái tiếng Việt
                    decryptedText.append(currentChar);
                }
            }
        } else {
            System.out.println("Không thể giải mã: a không có phần tử nghịch đảo trong hệ affine.");
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        // Văn bản cần mã hóa và giải mã
        String plaintext = "xin chào, đây là một ví dụ về mã hóa và giải mã affine";

        // Mã hóa văn bản
        String encryptedText = encrypt(plaintext);
        System.out.println("Văn bản đã mã hóa: " + encryptedText);

        // Giải mã văn bản
        String decryptedText = decrypt(encryptedText);
        System.out.println("Văn bản đã giải mã: " + decryptedText);
    }
}

