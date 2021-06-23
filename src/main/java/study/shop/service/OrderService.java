package study.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import study.shop.TextForOrder.Text;
import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;
import study.shop.io.WriteDataInFileI;
import study.shop.io.WriteDataInFileW;
import study.shop.model.Card;
import study.shop.model.Order;
import study.shop.model.User;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Optional;


public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private CardService cardService = new CardService();
    private WriteDataInFileI writeToFile = new WriteDataInFileW();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd HH-mm");
    private GregorianCalendar date = new GregorianCalendar();

    private static long idReceipt = 1;

    public OrderService() throws ServiceException {
    }

    public String orderPayment(Order order, User user) throws ServiceException {
        StringBuffer receipt = new StringBuffer();
        try {
            Text bText = new Text();
            Optional<Card> optionalCard = cardService.getCard(order.getIdCardUser());
            String numberOrder = getNumberOrder();
            receipt.append(bText.headReceipt(numberOrder, idReceipt++))
                    .append(optionalCard.map(Card::getIdCard).orElse("No card")).append("\n")
                    .append(bText.getLineChair(DataShop.CHAR_EQUALS, DataShop.LIMIT_CHAR_FOR_RECEIPT))
                    .append(bText.listItemsReceipt(order))
                    .append(bText.finalPriceReceipt(order, optionalCard.orElse(new Card("No", 0))));

            bText.createFileForOrder(order, user, numberOrder);
            writeToFile.printToFile(receipt.toString(), numberOrder, DataShop.PATH_RECEIPT_PRINT);

            return receipt.toString();
        }catch (ServiceException e) {
            logger.warn(DataShop.ERROR_ORDER_PAYMENT, e);
            throw new ServiceException(e);
        }
    }

    private String getNumberOrder() {
        return dateFormat.format(new GregorianCalendar().getTime()) + " " + (int)(Math.random() * 10000);
    }
}
