/*----------------------------------------------------------------------------------------------------------------------
	CommandLineUtil sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util;

import java.util.Scanner;

public final class CommanLineUtil {
    private CommanLineUtil()
    {}

    public static String [] getArguments(String [] args, String prompt, Scanner scanner)
    {
        if (args.length > 0)
            return args;

        System.out.print(prompt + ":");

        return scanner.nextLine().split("[ \t]+");
    }
}
