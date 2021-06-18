package study.shop.service;

import study.shop.model.Card;

import static org.junit.Assert.*;

public class CardServiceTest {

    CardService cardService = new CardService();

    @org.junit.Test
    public void isDiscount() {
        int numberCard = 7777;
        boolean result = cardService.isDiscount(numberCard);
        assertTrue(result);
    }

    @org.junit.Test
    public void isNotDiscount() {
        int numberCard = 77777777;
        boolean result = cardService.isDiscount(numberCard);
        assertFalse(result);
    }

    @org.junit.Test
    public void getCard() {
        int numberCard = 1111;
        Card expectedCard = new Card(1111, 0.3);
        Card actualCard = cardService.getCard(numberCard);
        assertEquals(expectedCard, actualCard);
    }

    @org.junit.Test
    public void getNotCard() {
        int numberCard = 7777777;
        Card expectedCard = new Card(0, 0.0);
        Card actualCard = cardService.getCard(numberCard);
        assertEquals(expectedCard, actualCard);
    }
}