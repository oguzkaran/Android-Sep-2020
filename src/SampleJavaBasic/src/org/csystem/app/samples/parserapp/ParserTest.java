package org.csystem.app.samples.parserapp;

import java.util.Scanner;

public final class ParserTest {
    private ParserTest()
    {
    }

    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Parser parser = new Parser();

        for (;;) {
            System.out.print("Bir yazı giriniz:");
            String str = kb.nextLine();

            if (str.equals("quit"))
                break;

            StringISource stringSource = new StringISource(str);
            CharArrayISource charArraySource = new CharArrayISource(str + str);

            parser.setSource(stringSource);

            parser.doParse();

            parser.setSource(charArraySource);

            parser.doParse();
        }

        System.out.println("Tekrar yapıyor musunuz?");
    }
}
