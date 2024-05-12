package elgamal;

import java.util.Random;

public class ElGamal {


    private static final int p = 23;

    private static final int g = 5;


    public static int[] generateKeyPair() {
        Random random = new Random();


        int x = random.nextInt(p - 3) + 2;


        int y = modPow(g, x, p);

        return new int[]{y, x};
    }

    // Hàm mã hóa
    public static int[] encrypt(int publicKey, int plaintext) {
        Random random = new Random();


        int k = random.nextInt(p - 2) + 1;


        int c1 = modPow(g, k, p);


        int c2 = (plaintext * modPow(publicKey, k, p)) % p;

        return new int[]{c1, c2};
    }


    public static int decrypt(int privateKey, int[] ciphertext) {
        int c1 = ciphertext[0];
        int c2 = ciphertext[1];


        int c1PowX = modPow(c1, privateKey, p);


        int c1Inverse = modInverse(c1PowX, p);


        int decrypted = (c2 * c1Inverse) % p;
        if (decrypted < 0) {
            decrypted += p;
        }
        return decrypted;
    }


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


    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] keyPair = generateKeyPair();
        int publicKey = keyPair[0];
        int privateKey = keyPair[1];


        int plaintext = 10;


        int[] ciphertext = encrypt(publicKey, plaintext);


        int decryptedPlaintext = decrypt(privateKey, ciphertext);


        System.out.println("Tin nhắn gốc: " + plaintext);
        System.out.println("Tin nhắn đã giải mã: " + decryptedPlaintext);
    }
}
