package study.shop.service;

import study.shop.exception.ServiceException;
import study.shop.model.Card;

import java.util.Optional;

import static org.junit.Assert.*;

public class CardServiceTest {

    CardService cardService;

    {
        try {
            cardService = new CardService();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

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
        String numberCard = "1111";
        Card expectedCard = new Card("1111", 0.3);
        Card actualCard = cardService.getCard(numberCard).orElse(new Card("No", 0));
        assertEquals(expectedCard, actualCard.getIdCard());
    }

    @org.junit.Test
    public void getNotCard() {
        String numberCard = "7777777";
        Card expectedCard = new Card("0", 0.0);
        Optional<Card> actualCard = cardService.getCard(numberCard);
        assertEquals(expectedCard, actualCard);
    }
}