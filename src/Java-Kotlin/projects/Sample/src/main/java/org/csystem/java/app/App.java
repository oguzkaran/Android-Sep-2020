package org.csystem.java.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    public static void main(String[] args) throws InterruptedException
    {
        var sample = new Sample();
        var kb = new Scanner(System.in);

        System.out.print("Kaç thread yaratmak istiyorsunuz?");
        var n = Integer.parseInt(kb.nextLine());
        var threads = new ArrayList<Thread>();

        for (var i = 0; i < n; ++i) {
            var thread = new Thread(sample::threadProc);
            thread.start();
            threads.add(thread);
        }

        for (var t : threads)
            t.join();

        System.out.printf("Size:%d%n", sample.getSize());
    }
}

class Sample {
    private final List<Integer> m_intList = new ArrayList<>();

    private synchronized void addItem(int value)
    {
        ++value;

        m_intList.add(value);
    }
    public int getSize()
    {
        return m_intList.size();
    }

    public void threadProc()
    {
        for (var i = 0; i < 1000000; ++i)
            addItem(i);
    }
}
