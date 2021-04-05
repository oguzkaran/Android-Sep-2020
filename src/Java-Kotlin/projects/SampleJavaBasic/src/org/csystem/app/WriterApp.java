/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki örnek Files sınıfının new BufferedWriter metodu ile de yapılabilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.CommanLineUtil;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

class WriterApp {
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        args = CommanLineUtil.getArguments(args, "Yol ifadesini giriniz", kb);

        if (args.length != 1) {
            System.out.println("Wrong usage");
            System.exit(-1);
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(args[0]), StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            for (;;) {
                System.out.print("Bir yazı giriniz:");
                String text = kb.nextLine();

                if (text.equals("quit"))
                    break;

                bufferedWriter.write(text);
                bufferedWriter.newLine();
            }
        }
        catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
    }
}
