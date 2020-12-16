package org.csystem.java.app;

import org.csystem.app.samples.ConsoleUtil;

class App {
    public static void main(String[] args)
    {
        var a = ConsoleUtil.readInt("Bir sayı giriniz:", "Hatalı sayı girdiniz");

        System.out.println(a * a);
    }
}



