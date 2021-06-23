package study.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;
import study.shop.io.ReadDataFromFileW;
import study.shop.model.Card;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardService {

    private static final Logger logger = LogManager.getLogger(CardService.class);

    private List<Card> listCard = new ArrayList<>();
    private static final String REGEX_NUMBER = "N[\\w]*";
    private static final String REGEX_PERCENT = "P\\d*\\.?\\d*";

    public CardService() throws ServiceException {
        try {
//            if(Files.exists(Paths.get(DataShop.PATH_FILE_CARD))) {
                ReadDataFromFileW readDataFromFileW = new ReadDataFromFileW();
                List<String> linesList = readDataFromFileW.readAll(DataShop.PATH_FILE_CARD);
                for (String string : linesList) {
                    listCard.add(new Card(ReadDataFromFileW.parseData(string, REGEX_NUMBER),
                            Double.parseDouble(ReadDataFromFileW.parseData(string, REGEX_PERCENT))));
                }
//            }
        }catch (ServiceException e) {
            logger.warn(DataShop.ERROR_CARD_NOT_INIT + DataShop.PATH_FILE_CARD, e);
            throw new ServiceException(e);
        }
    }

    public boolean isDiscount(long numberCardUser) {
        for(Card number : listCard) {
            if(number.getIdCard().equals(numberCardUser)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Card> getCard(String numberCardUser) {
        Card card = new Card();
        for(Card number : listCard) {
            if(number.getIdCard().equals(numberCardUser)) {
                card.setIdCard(number.getIdCard());
                card.setPercent(number.getPercent());
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }
}
