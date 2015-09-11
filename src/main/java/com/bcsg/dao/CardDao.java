package com.bcsg.dao;

import com.bcsg.bean.Card;

import java.util.Collection;
import java.util.List;

/**
 * DAO for cards.
 */
public interface CardDao {

    /**
     * Get all cards of the database.
     *
     * @return a list of cards
     */
    Collection<Card> getAll();

    /**
     * Adds a new card in the database.
     *
     * @param card a card
     */
    void add(Card card);

    /**
     * Add multiple cards in the database.
     *
     * @param cards a list of cards
     */
    void addAll(List<Card> cards);
}
