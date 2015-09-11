package com.bcsg.dao;

import com.bcsg.bean.Card;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * User's session implementation of CardDao.
 *
 * Use this implementation only for prototyping and demonstration purpose.
 */
@Component
@Scope(value="session")
public class UserSessionCardDao implements CardDao {

    private Set<Card> cardSet;

    /**
     * Constructor.
     */
    public UserSessionCardDao() {
        cardSet = new HashSet<>();
    }

    @Override
    public Collection<Card> getAll() {
        return cardSet;
    }

    @Override
    public void add(final Card card) {
        cardSet.add(card);
    }

    @Override
    public void addAll(final List<Card> cards) {
        cardSet.addAll(cards);
    }
}
