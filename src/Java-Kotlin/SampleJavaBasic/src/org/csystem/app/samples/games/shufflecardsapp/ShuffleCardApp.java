package org.csystem.app.samples.games.shufflecardsapp;

import org.csystem.app.samples.games.cardgames.Card;

public final class ShuffleCardApp {
    private ShuffleCardApp()
    {
    }

    public static void run()
    {
        Card[] deck;

        deck = Card.getShuffledDeck();

        for (Card c : deck)
            System.out.println(c);
    }
}
