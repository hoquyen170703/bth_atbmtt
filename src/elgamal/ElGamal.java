package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {

    // Khai báo các thành phần của khóa ElGamal
    private BigInteger p; // Prime number
    private BigInteger g; // Generator
    private BigInteger x; // Private key
    private BigInteger y; // Public key

    // Hàm khởi tạo để tạo ra cặp khóa ElGamal
    public ElGamal(int bitLength) {
        // Khởi tạo đối tượng SecureRandom để sinh số ngẫu nhiên
        SecureRandom random = new SecureRandom();

        // Sinh số nguyên tố lớn p và q
        p = BigInteger.probablePrime(bitLength, random);

        // Sinh số nguyên tố g, là một nguyên tố nguyên tố của p-1
        g = BigInteger.valueOf(2);
        while (g.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
            g = BigInteger.probablePrime(bitLength, random);
        }

        // Sinh khóa bí mật x, là một số nguyên ngẫu nhiên nhỏ hơn p
        x = new BigInteger(bitLength, random);
        while (x.compareTo(p) >= 0) {
            x = new BigInteger(bitLength, random);
        }

        // Tính khóa công khai y
        y = g.modPow(x, p);
    }

    // Phương thức để mã hóa thông điệp
    public BigInteger[] encrypt(BigInteger message) {
        SecureRandom random = new SecureRandom();
        BigInteger k;
        do {
            k = new BigInteger(p.bitLength(), random);
        } while (k.compareTo(p.subtract(BigInteger.ONE)) >= 0 || k.compareTo(BigInteger.TWO) <= 0);

        BigInteger a = g.modPow(k, p);
        BigInteger b = message.multiply(y.modPow(k, p)).mod(p);
        return new BigInteger[]{a, b};
    }

    // Phương thức để giải mã bản mã ElGamal
    public BigInteger decrypt(BigInteger[] ciphertext) {
        BigInteger a = ciphertext[0];
        BigInteger b = ciphertext[1];
        BigInteger s = a.modPow(x, p);
        BigInteger inverseS = s.modInverse(p);
        return b.multiply(inverseS).mod(p);
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng ElGamal với độ dài khóa là 128 bit
        ElGamal elGamal = new ElGamal(128);

        // Thông điệp cần mã hóa
        BigInteger message = new BigInteger("12345");

        // Mã hóa thông điệp
        BigInteger[] ciphertext = elGamal.encrypt(message);

        // Hiển thị bản mã ElGamal
        System.out.println("Ciphertext: (" + ciphertext[0] + ", " + ciphertext[1] + ")");

        // Giải mã bản mã ElGamal
        BigInteger decryptedMessage = elGamal.decrypt(ciphertext);

        // Hiển thị thông điệp đã giải mã
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
