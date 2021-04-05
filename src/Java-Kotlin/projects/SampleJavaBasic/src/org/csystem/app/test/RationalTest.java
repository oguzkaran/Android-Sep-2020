package org.csystem.app.test;

import org.csystem.math.Rational;
import org.csystem.math.RationalException;

import java.util.Scanner;

public final class RationalTest {
    private RationalTest()
    {
    }

    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Rational rational = new Rational();

        for (int i = 0; i  < 5; ++i) {
            try {
                System.out.print("Pay değerini giriniz:");
                int a = Integer.parseInt(kb.nextLine());

                System.out.print("Payda değerini giriniz:");
                int b = Integer.parseInt(kb.nextLine());

                rational.setNumerator(a);
                rational.setDenominator(b);

                System.out.printf("Kesir:%s%n", rational);
            }
            catch (RationalException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
