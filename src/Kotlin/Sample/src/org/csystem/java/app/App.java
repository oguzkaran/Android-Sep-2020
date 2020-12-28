package org.csystem.java.app;

class App {
    public static void main(String[] args)
    {
        Class<?> clsInt = int.class;
        Class<String> clsString = String.class;
        String s = "ankara";

        Class<?> clsStr = s.getClass();

        System.out.println(clsStr == clsString);

        for (var ctor : clsStr.getDeclaredConstructors()) {
            for (var parameterTypes : ctor.getParameterTypes())
                System.out.printf("%s ", parameterTypes.getName());


            System.out.println();
        }
    }
}



