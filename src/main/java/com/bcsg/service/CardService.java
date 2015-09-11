package com.bcsg.service;

import com.bcsg.bean.Card;
import com.bcsg.dao.CardDao;
import org.joda.time.LocalDate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This class provides services for the webApp.
 * - adding new cards
 * - upload card using csv file
 * - get cards
 */
@Service
public class CardService {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CardService.class);

    @Inject
    private CardDao dao;

    private static Date getLastDayOfMonth(final Date expiry) {
        final LocalDate date = new LocalDate(expiry);
        return getLastDayOfMonth(date.getMonthOfYear(), date.getYear());
    }

    private static Date getLastDayOfMonth(final String expiry) {
        final String[] monthYear = expiry.split("/");
        return getLastDayOfMonth(monthYear[0], monthYear[1]);
    }

    private static Date getLastDayOfMonth(final String month, final String year) {
        return getLastDayOfMonth(Integer.parseInt(month), Integer.parseInt(year));
    }

    private static Date getLastDayOfMonth(final int month, final int year) {
        final LocalDate lastDayOfMonth = new LocalDate(year, month, 1).dayOfMonth().withMaximumValue();
        return lastDayOfMonth.toDate();
    }

    private static Card card(final String csvLine) {
        final String[] data = csvLine.split(";");
        return new Card(data[0], data[1], getLastDayOfMonth(data[2]));
    }

    /**
     * Returns the cards stored in the database ordered by expiry date in descending order.
     *
     * @return cards ordered by expiry date in descending order
     */
    public Collection<Card> getAllCards() {

        final ArrayList<Card> cards = new ArrayList<>(dao.getAll());

        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getExpiry().compareTo(o2.getExpiry());
            }
        });

        return cards;
    }

    /**
     * Adds the specified card to the database.
     *
     * @param bank   bank name
     * @param number card number
     * @param expiry expiry date (MM/yyyy)
     */
    public void addCard(final String bank, final String number, final Date expiry) {
        dao.add(new Card(bank, number, getLastDayOfMonth(expiry)));
    }

    /**
     * Adds to database the cards of the specified CSV file.
     *
     * @param file a CSV file
     * @throws IOException if unable to read file
     */
    public void addCardCsv(final MultipartFile file) throws IOException {

        final List<Card> cards = new ArrayList<>();

        String line = null;

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            try {
                while ((line = br.readLine()) != null) {
                    cards.add(card(line));
                }
            } catch (Exception ex) {
                // gently ignore any malformed line
                logger.warn("malformed line in CSV file: " + line);
            }
        }

        dao.addAll(cards);

    }

}
