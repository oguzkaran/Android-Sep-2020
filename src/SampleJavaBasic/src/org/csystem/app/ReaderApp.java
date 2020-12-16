/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki örnek Files sınıfının new BufferedReader metodu ile de yapılabilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.CommanLineUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class ReaderApp {
    public static void main(String[] args)
    {
        args = CommanLineUtil.getArguments(args, "Yol ifadesini giriniz", new Scanner(System.in));

        if (args.length != 1) {
            System.out.println("Wrong usage");
            System.exit(-1);
        }

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(args[0]), StandardCharsets.UTF_8)) {
            String line;

            while ((line = bufferedReader.readLine()) != null)
                System.out.println(line);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı");
            System.out.printf("Message:%s%n", ex.getMessage());
        }
        catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
    }
}
