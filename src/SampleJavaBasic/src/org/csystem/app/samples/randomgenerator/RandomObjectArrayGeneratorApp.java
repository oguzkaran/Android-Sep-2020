package org.csystem.app.samples.randomgenerator;

import org.csystem.util.ArrayUtil;
import org.csystem.util.datetime.Date;
import org.csystem.util.datetime.Time;

public final class RandomObjectArrayGeneratorApp {
    private RandomObjectArrayGeneratorApp()
    {}

    public static void run()
    {
        RandomObjectArrayGenerator randomObjectArrayGenerator = new RandomObjectArrayGenerator(5);

        randomObjectArrayGenerator.run();

        for (Object object : randomObjectArrayGenerator.getObjects()) {
            System.out.printf("Dinamik tür ismi:%s%n", object.getClass().getName());

            if (object instanceof String) {
                String s = (String)object;

                System.out.printf("Yazı:%s%n", s);
                System.out.printf("Yazı:%s%n", s.toUpperCase());
            }
            else if (object instanceof Integer) {
                int val = (int)object;

                System.out.printf("%d * %d = %d%n", val, val, val * val);
            }
            else if (object instanceof Double) {
                double dVal = (double)object;

                System.out.printf("dVal=%f%n", dVal);
            }
            else if (object instanceof Boolean) {
                boolean flag = (boolean)object;

                System.out.printf("flag=%b%n", flag);
            }
            else if (object instanceof Date) {
                Date date = (Date)object;

                System.out.println(date.toLongDateStringTR());
            }
            else if (object instanceof Time) {
                Time time = (Time)object;

                System.out.println(time.toLongTimeString());
            }
            else if (object instanceof int []) {
                int [] a = (int[])object;

                ArrayUtil.display(3, a);
            }
        }
    }
}
