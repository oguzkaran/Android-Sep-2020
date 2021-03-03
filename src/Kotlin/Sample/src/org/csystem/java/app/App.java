package org.csystem.java.app;

class App {
    public static void main(String[] args)
    {
        var a = new A();
        var x = a.new B();
    }
}

class A {
    public class B {

    }
}
