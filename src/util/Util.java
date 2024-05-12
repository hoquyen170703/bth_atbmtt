package util;

import java.math.BigInteger;

public class Util {
    public static Integer gcd(Integer a, Integer b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);

    }


    public static BigInteger modInverse(BigInteger a, BigInteger n) {
        BigInteger r0 = n;
        BigInteger r1 = a;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        while (!r1.equals(BigInteger.ZERO)) {
            BigInteger q = r0.divide(r1);
            BigInteger r2 = r0.subtract(q.multiply(r1));
            BigInteger x2 = x0.subtract(q.multiply(x1));
            BigInteger y2 = y0.subtract(q.multiply(y1));

            r0 = r1;
            r1 = r2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }

        if (!r0.equals(BigInteger.ONE))
            return BigInteger.valueOf(-1); // Không tồn tại phần tử nghịch đảo

        if (x0.compareTo(BigInteger.ZERO) < 0)
            x0 = x0.add(n);
        return x0;

    }


    public static BigInteger modPow(byte[] a, BigInteger b, BigInteger n) {
        BigInteger f = BigInteger.ONE;
        BigInteger base = new BigInteger(a);

        while (b.compareTo(BigInteger.ZERO) > 0) {
            if (b.testBit(0)) {
                f = f.multiply(base).mod(n);
            }
            base = base.multiply(base).mod(n);
            b = b.shiftRight(1);
        }
        return f;
    }

}
