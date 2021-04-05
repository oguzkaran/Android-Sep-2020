package org.csystem.app.test;

import org.csystem.util.datetime.DateTime;
import org.csystem.util.datetime.DateTimeException;

import java.util.Scanner;

public final class DateTimeTest {
    private DateTimeTest()
    {
    }

    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        DateTime dateTime = new DateTime();

        for (int i = 0; i  < 5; ++i) {
            try {
                System.out.print("Gün bilgisini giriniz:");
                int day = Integer.parseInt(kb.nextLine());

                System.out.print("Saat bilgisini giriniz:");
                int hour = Integer.parseInt(kb.nextLine());

                dateTime.setDay(day);
                dateTime.setHour(hour);

                System.out.println(dateTime.toLongDateStringTR());
                System.out.println(dateTime.toLongTimeString());
                System.out.println(dateTime.toString());
            }
            catch (DateTimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
