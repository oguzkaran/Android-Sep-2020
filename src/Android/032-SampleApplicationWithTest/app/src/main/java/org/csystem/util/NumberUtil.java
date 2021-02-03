package org.csystem.util;

public final class NumberUtil {
    private static int getDigitsPowSum(int a)
    {
        int n = a == 0 ? 1 : (int)Math.log10(a) + 1;
        int total = 0;

        while (a != 0) {
            total += Math.pow(a % 10, n);
            a /= 10;
        }

        return total;
    }

    private NumberUtil()
    {
    }

    public static long factorial(int n)
    {
        long result = 1;

        for (long i = 2; i <= n; ++i)
            result *= i;

        return result;
    }

    public static boolean isArmstrong(int a)
    {
        if (a < 0)
            return false;

        return getDigitsPowSum(a) == a;
    }

    public static int add(int a, int b)
    {
        return a + b;
    }

    public static boolean isPrime(int value)
    {
        if (value <= 1)
            return false;

        if (value % 2 == 0)
            return value == 2;

        if (value % 3 == 0)
            return value == 3;

        if (value % 5 == 0)
            return value == 5;

        if (value % 7 == 0)
            return value == 7;

        int sqrtValue = (int)Math.sqrt(value);

        for (int i = 11; i <= sqrtValue; i += 2)
            if (value % i == 0)
                return false;

        return true;
    }
    //...
}
