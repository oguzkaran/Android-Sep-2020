/*----------------------------------------------------------------------------------------------------------------------
	Console sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util;

import java.util.Scanner;

public final class Console {
    private static final Scanner KB;

    static {
        KB = new Scanner(System.in);
    }

    private Console()
    {
    }

    public static int readIntLine(String message)
    {
        return readInt(message + "\n");
    }

    public static int readIntLine(String message, String errorMessage)
    {
        return readInt(message + "\n", errorMessage + "\n");
    }

    public static int readInt()
    {
        return readInt("");
    }

    public static int readInt(String message)
    {
        return readInt(message, "");
    }

    public static int readInt(String message, String errorMessage)
    {
        for (;;) {
            try {
                System.out.print(message);

                return Integer.parseInt(KB.nextLine());
            }
            catch (NumberFormatException ex) {
                System.out.print(errorMessage);
            }
        }
    }

    public static double readDoubleLine(String message)
    {
        return readDouble(message + "\n");
    }

    public static double readDoubleLine(String message, String errorMessage)
    {
        return readDouble(message + "\n", errorMessage + "\n");
    }

    public static double readDouble()
    {
        return readDouble("");
    }

    public static double readDouble(String message)
    {
        return readDouble(message, "");
    }

    public static double readDouble(String message, String errorMessage)
    {
        for (;;) {
            try {
                System.out.print(message);

                return Double.parseDouble(KB.nextLine());
            }
            catch (NumberFormatException ex) {
                System.out.print(errorMessage);
            }
        }
    }

    //...
    public static void writeLine(String format, Object...objects)
    {
        System.out.printf(format + "\n", objects);
    }
    //...
}
