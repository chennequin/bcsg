package com.bcsg.dao;

import com.bcsg.bean.Card;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Jdbc implementation of CardDao.
 */
@Repository
public class JdbcCardDao extends JdbcDaoSupport implements CardDao {

    private static final String SELECT_ALL = "select bank, card_number, expiry_date from CARD";

    final String INSERT_SQL = "insert into CARD (bank, card_number, expiry_date) values(?, ?, ?)";

    public Collection<Card> getAll() {

        final List<Card> cards = this.getJdbcTemplate().query(
                SELECT_ALL,
                new RowMapper<Card>() {
                    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Card card = new Card(
                                rs.getString("bank"),
                                rs.getString("card_number"),
                                rs.getDate("expiry_date")
                        );
                        return card;
                    }
                });

        return cards;
    }

    @Override
    public void add(final Card card) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                        final PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, card.getBank());
                        ps.setString(2, card.getNumber());
                        ps.setDate(3, new java.sql.Date(card.getExpiry().getTime()));
                        return ps;
                    }
                },
                keyHolder);
    }

    @Override
    public void addAll(final List<Card> cards) {

        getJdbcTemplate().batchUpdate(INSERT_SQL,
                new BatchPreparedStatementSetter() {
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setString(1, cards.get(i).getBank());
                        ps.setString(2, cards.get(i).getNumber());
                        ps.setDate(3, new java.sql.Date(cards.get(i).getExpiry().getTime()));
                    }

                    public int getBatchSize() {
                        return cards.size();
                    }
                });

    }

}
