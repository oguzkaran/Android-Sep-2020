package org.csystem.app.samples.games.cardgames;

public final class CardTestApp {
    private CardTestApp()
    {
    }

    public static void main(String[] args)
    {
        run();
    }

    public static void run()
    {
        String name1 = "Kupa-Papaz";
        String name2 = "Karo-Kız";
        String name3 = "Kupa-Vale";
        String name4 = "Kupa-vale";
        String name5 = "Kupa";

        Card card = new Card(name1);

        System.out.println(card);

        card = new Card(name2);

        System.out.println(card);

        card = new Card(name3);

        System.out.println(card);

        card = new Card(name5);

        System.out.println(card);
    }
}
