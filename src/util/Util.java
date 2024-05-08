package util;

public class Util {
    public static Integer gcd(Integer a, Integer b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);

    }

    public static Integer modInverse(Integer a, Integer n) {
        int r0 = n;
        int r1 = a;
        int x0 = 1;
        int x1 = 0;
        int y0 = 0;
        int y1 = 1;

        while (r1 != 0) {
            int q = r0 / r1;
            int r2 = r0 - q * r1;
            int x2 = x0 - q * x1;
            int y2 = y0 - q * y1;

            r0 = r1;
            r1 = r2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }

        if (r0 != 1)
            return -1; // Không tồn tại phần tử nghịch đảo

        if (x0 < 0)
            x0 += n;
        return x0;

    }

    public static Integer modPow(Integer a, Integer b, Integer n) {
        int f = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                f = (f * a) % n;
            }
            a = (a * a) % n;
            b >>= 1;
        }
        return f;
    }
}
