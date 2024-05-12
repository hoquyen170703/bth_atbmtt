package elgamal;

import java.util.Random;

public class ElGamal {

    // Số nguyên tố p nhỏ
    private static final int p = 23;

    // Phần tử gốc của nhóm
    private static final int g = 5;

    // Hàm sinh cặp khóa
    public static int[] generateKeyPair() {
        Random random = new Random();

        // Chọn số ngẫu nhiên x trong đoạn [2, p - 2]
        int x = random.nextInt(p - 3) + 2;

        // Tính y = g^x mod p
        int y = modPow(g, x, p);

        return new int[]{y, x};
    }

    // Hàm mã hóa
    public static int[] encrypt(int publicKey, int plaintext) {
        Random random = new Random();

        // Chọn số ngẫu nhiên k trong đoạn [1, p - 2]
        int k = random.nextInt(p - 2) + 1;

        // Tính c1 = g^k mod p
        int c1 = modPow(g, k, p);

        // Tính c2 = (plaintext * publicKey^k) mod p
        int c2 = (plaintext * modPow(publicKey, k, p)) % p;

        return new int[]{c1, c2};
    }

    // Hàm giải mã
    public static int decrypt(int privateKey, int[] ciphertext) {
        int c1 = ciphertext[0];
        int c2 = ciphertext[1];

        // Tính c1^x mod p
        int c1PowX = modPow(c1, privateKey, p);

        // Tính phần tử nghịch đảo của c1^x mod p
        int c1Inverse = modInverse(c1PowX, p);

        // Giải mã: M = c2 * (c1^x)^(-1) mod p
        int decrypted = (c2 * c1Inverse) % p;
        if (decrypted < 0) {
            decrypted += p;
        }
        return decrypted;
    }

    // Hàm tính a^b mod m
    private static int modPow(int a, int b, int m) {
        int result = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            b >>= 1;
            a = (a * a) % m;
        }
        return result;
    }

    // Hàm tính phần tử nghịch đảo mod m
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // Không tồn tại phần tử nghịch đảo
    }

    public static void main(String[] args) {
        // Sinh cặp khóa
        int[] keyPair = generateKeyPair();
        int publicKey = keyPair[0];
        int privateKey = keyPair[1];

        // Tin nhắn cần mã hóa
        int plaintext = 10;

        // Mã hóa
        int[] ciphertext = encrypt(publicKey, plaintext);

        // Giải mã
        int decryptedPlaintext = decrypt(privateKey, ciphertext);

        // In ra kết quả
        System.out.println("Tin nhắn gốc: " + plaintext);
        System.out.println("Tin nhắn đã giải mã: " + decryptedPlaintext);
    }
}
