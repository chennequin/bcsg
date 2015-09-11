package com.bcsg.bean;

import org.springframework.util.Assert;

import java.util.Date;

/**
 * This class represents a card.
 */
public class Card implements Comparable<Card> {

    private String bank;
    private String number;
    private Date expiry;

    /**
     * Constructor.
     *
     * @param bank bank name
     * @param number number
     * @param expiry expiry date
     */
    public Card(final String bank, final String number, final Date expiry) {
        Assert.hasLength(bank);
        Assert.hasLength(number);
        Assert.notNull(expiry);
        this.bank = bank;
        this.number = number;
        this.expiry = expiry;
    }

    public String getBank() {
        return bank;
    }

    public String getNumber() {
        return number;
    }

    public String getTruncatedNumber() {
        return number.substring(0,4) + number.substring(4).replaceAll("[0-9]", "x");
    }

    public Date getExpiry() {
        return expiry;
    }

    /**
     * Defines the natural order of cards.
     *
     * @param o a card to compare
     * @return
     */
    @Override
    public int compareTo(final Card o) {
        return number.compareTo(o.number);
    }

    /**
     * Two card are equals if they have the same number.
     *
     * Ensure equals() is consistent with compareTo().
     *
     * @param o a card
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return number.equals(card.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
