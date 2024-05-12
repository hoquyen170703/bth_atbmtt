package rsa;

import util.Util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private final BigInteger privateKey;
    private BigInteger publicKey;
    private final BigInteger modulus;

    public RSA(int bitLength) {
        SecureRandom random = new SecureRandom();

        // Sinh 2 so nguyen to ngau nhien
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        // Tinh n
        modulus = p.multiply(q);

        // Tinh phi
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Chon so nguyen to ngau nhien
        do {
            publicKey = new BigInteger(bitLength, random);
        } while (publicKey.compareTo(BigInteger.ONE) <= 0 || publicKey.compareTo(phi) >= 0 || !publicKey.gcd(phi).equals(BigInteger.ONE));

        // tinh privateKey
        privateKey = publicKey.modInverse(phi);
    }

    public byte[] encrypt(byte[] message) {
        return new BigInteger(String.valueOf(Util.modPow(message, publicKey, modulus))).toByteArray();
    }

    public byte[] decrypt(byte[] encryptedMessage) {
        return new BigInteger(String.valueOf(Util.modPow(encryptedMessage, privateKey, modulus))).toByteArray();
    }

    public static void main(String[] args) {
        int bitLength = 128;
        RSA rsa = new RSA(bitLength);

        String message = "Hello, RSA!";
        byte[] encrypted = rsa.encrypt(message.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + new String(encrypted));
        System.out.println("Decrypted message: " + new String(decrypted));
    }
}


