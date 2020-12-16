package org.csystem.app.samples.games.cardgames;

import java.util.Random;

public class Card
{
    private static final Card [] DECK = getNewDeck();
    private CardValue m_value;
    private CardType m_type;

    private static void swap(Card [] deck, int i, int k)
    {
        Card temp = deck[i];

        deck[i] = deck[k];
        deck[k] = temp;
    }

    private static void shuffle(Card [] deck, int n)
    {
        Random r = new Random();

        for (int i = 0; i < n; ++i) {
            int first = r.nextInt(52);
            int second = r.nextInt(52);

            swap(deck, first, second);
        }
    }

    private static void doWorkForException(String message)
    {
        throw new IllegalArgumentException(message);
    }

    private static Card[] getNewDeck()
    {
        Card [] deck = new Card[52];
        int i = 0;

        for (CardType cardType : CardType.values())
            for (CardValue cardValue : CardValue.values())
                deck[i++] = new Card(cardValue, cardType);

        return deck;
    }

    private void setCard(String name)
    {
        String [] cardInfo = name.split("[-]");

        if (cardInfo.length != 2)
            doWorkForException("Geçersiz kart ismi");

        CardType cardType = CardType.getCardTypeByString(cardInfo[0]);
        CardValue cardValue = CardValue.getCardValueByString(cardInfo[1]);

        if (cardValue == null | cardType == null)
            doWorkForException("Geçersiz kart");

        m_type = cardType;
        m_value = cardValue;
    }

    public Card(CardValue cardValue, CardType cardType)
    {
        m_value = cardValue;
        m_type = cardType;
    }

    public Card(String name)
    {
        this.setName(name);
    }

    public CardValue getValue()
    {
        return m_value;
    }

    public void setValue(CardValue value)
    {
        m_value = value;
    }

    public CardType getType()
    {
        return m_type;
    }

    public void setType(CardType type)
    {
        m_type = type;
    }

    public String getName()
    {
        return String.format("%s-%s", m_type.getTypeStr(), m_value.getValueStr());
    }

    public void setName(String name)
    {
        this.setCard(name);
    }

    public String toString()
    {
        return getName();
    }

    public static Card[] getShuffledDeck()
    {
        shuffle(DECK, 100);

        return DECK;
    }
}
