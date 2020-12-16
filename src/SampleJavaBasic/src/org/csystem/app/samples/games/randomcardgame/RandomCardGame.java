package org.csystem.app.samples.games.randomcardgame;

import org.csystem.app.samples.games.cardgames.Card;
import org.csystem.app.samples.games.template.AbstractGame;

import java.util.Scanner;

public class RandomCardGame extends AbstractGame {
    private Scanner m_scanner;
    private int m_count;
    private int m_score;

    private void playGame(Card card)
    {
        Card [] deck = Card.getShuffledDeck();

        // Seçilen kartın index numarası bulunup sonuç belirlenebilir. enum'ların eşitlik karşılaştırmasını göstermek
        //için aşağıdaki gibi yazdık

        boolean turnFlag = true;

        for (Card c : deck) {
            if (c.getValue() == card.getValue() && c.getType() == card.getType()) {
                if (turnFlag) {
                    ++m_score;
                    System.out.println("Sen kazandın");
                }
                else
                    System.out.println("ben kazandım");

                break;
            }

            turnFlag = !turnFlag;
        }
    }

    public RandomCardGame()
    {
        super("Random Card Generator");
    }

    public void start()
    {
        m_scanner = new Scanner(System.in);
        System.out.print("Kaç kez oynamak istersiniz:");
        m_count = Integer.parseInt(m_scanner.nextLine());
    }

    public void play()
    {
        for (int i = 0; i < m_count; ++i) {
            System.out.print("Lütfen bir kart seçiniz");
            String name = m_scanner.nextLine();
            //...
            Card card = new Card(name);

            playGame(card);
        }

        System.out.printf("Sen:%d, Ben:%d%n", m_score, m_count - m_score);
    }

    public void pause()
    {
        System.out.println("RandomCardGame pause");
    }
    //...
    public void end()
    {
        System.out.println("RandomCardGame end");
    }
}
