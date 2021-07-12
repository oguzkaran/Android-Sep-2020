package org.csystem.java.app;

import java.util.Arrays;

class App {
    public static void main(String[] args)
    {
        int [] a = {1, 2, 3, 4, 5};
        int [] b = new int[5];

        System.out.println(Arrays.stream(a).reduce(Integer::sum));
    }
}

class Util {
    public static int sum(int a, int b)
    {
        return a + b;
    }
}


@FunctionalInterface
interface IBinaryOperator<T> {
    T apply(T left, T right);
}

@FunctionalInterface
interface IIntBinaryOperator {
    int applyAsInt(int left, int right);
}



class StringUtil {
    public static StringBuffer s;
    public static String changeCase(String str)
    {
        char [] chars = str.toCharArray();

        for (int i = 0; i < chars.length; ++i)
            chars[i] = Character.isUpperCase(chars[i]) ? Character.toLowerCase(chars[i]) :  Character.toUpperCase(chars[i]);

        return String.valueOf(chars);
    }
}
