package study.shop.service;

import study.shop.constant.DataShop;
import study.shop.io.ReaderData;
import study.shop.model.Card;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardService {

    private List<Card> listCard = new ArrayList<>();
    private static final String REGEX_NUMBER = "N[\\w]*";
    private static final String REGEX_PERCENT = "P\\d*\\.?\\d*";

    public CardService() {
        if(listCard.isEmpty()) {
            if(Files.exists(Paths.get(DataShop.PATH_FILE_CARD))) {
                ReaderData readerData = new ReaderData();
                List<String> linesList = readerData.readAll(DataShop.PATH_FILE_CARD);
                for (String string : linesList) {
                    listCard.add(new Card(Long.parseLong(ReaderData.parseData(string, REGEX_NUMBER)),
                            Double.parseDouble(ReaderData.parseData(string, REGEX_PERCENT))));
                }
            }
        }
    }

    public boolean isDiscount(long numberCardUser) {
        for(Card number : listCard) {
            if(number.getIdCard() == numberCardUser) {
                return true;
            }
        }
        return false;
    }

    public Card getCard(long numberCardUser) {
        Card card = new Card();
        for(Card number : listCard) {
            if(number.getIdCard() == numberCardUser) {
                card.setIdCard(number.getIdCard());
                card.setPercent(number.getPercent());
                return card;
            }
        }
        return new Card(0, 0.0);
    }
}
