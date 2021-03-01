package org.csystem.java.app;

class App {
    public static void main(String[] args)
    {
         var thread = new Thread(() -> {
             for (int i = 0; i < 10; ++i) {
                 System.out.printf("%d ", i);
                 ThreadUtil.sleep(1000);
             }
         });

         thread.start();
    }
}

class ThreadUtil {
    public static void sleep(long ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ignore) {

        }
    }
}

