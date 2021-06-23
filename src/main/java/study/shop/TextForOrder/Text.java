package study.shop.TextForOrder;

import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;
import study.shop.io.WriteDataInFileI;
import study.shop.io.WriteDataInFileW;
import study.shop.model.Card;
import study.shop.model.Cart;
import study.shop.model.Order;
import study.shop.model.User;
import study.shop.service.CardService;
import study.shop.service.CartService;

import java.util.GregorianCalendar;
import java.util.Optional;

public class Text {

    private int limitCharForOrder = DataShop.LIMIT_CHAR_FOR_ORDER;
    private int limitCharForReceipt = DataShop.LIMIT_CHAR_FOR_RECEIPT;
    private final String CHAR_EQUALS = DataShop.CHAR_EQUALS;
    private final String CHAR_SPACE = DataShop.CHARS_SPACE;
    private String date = new GregorianCalendar().getTime().toString();
    private WriteDataInFileI writeToFile = new WriteDataInFileW();

    public String getLineChair(String symbol, int count) {
        StringBuffer countSymbolLine = new StringBuffer();
        for(int i = 0; i < count; i++) {
            countSymbolLine.append(symbol);
        }
        return countSymbolLine.toString();
    }

    private String getSpace(String values1, String values2, int limitSpace) {
        int limit = limitSpace;
        StringBuffer countSpaceLine = new StringBuffer();
        int countSpace = limit - values1.length() - values2.length();
        if(countSpace >= 0) {
            for (int i = 0; i < countSpace; i++) {
                countSpaceLine.append(" ");
            }
        }
        return countSpaceLine.toString();
    }

    public int findMiddle(String text, int length) {
        return length / 2 - text.length() / 2;
    }

    public String headReceipt(String nameOrder, long idReceipt) {
        StringBuffer receipt = new StringBuffer();
        String receiptLine = DataShop.RECEIPT_NUMBER + idReceipt + "/" + nameOrder;

        receipt.append(getLineChair(CHAR_SPACE, findMiddle(DataShop.NAME_SHOP, limitCharForReceipt)))
                .append(DataShop.NAME_SHOP).append("\n")
                .append(getLineChair(CHAR_EQUALS, limitCharForReceipt)).append("\n")
                .append(getLineChair(CHAR_SPACE, findMiddle(DataShop.ADDRESS_SHOP,
                        limitCharForReceipt))).append(DataShop.ADDRESS_SHOP).append(" \n")
                .append(getLineChair(CHAR_EQUALS, limitCharForReceipt)).append("\n")
                .append(getLineChair(CHAR_SPACE, findMiddle(receiptLine,
                        limitCharForReceipt))).append(receiptLine)
                .append("\n").append(getLineChair(CHAR_EQUALS, limitCharForReceipt)).
                append("\n" + DataShop.USE_DISCOUNT_CARD);
        return receipt.toString();
    }

    public String finalPriceReceipt(Order order, Card card) {
        StringBuffer receipt = new StringBuffer();
        CartService cartService = new CartService();
        double totalPrice = cartService.getTotalPrice(order.getCartList(), card);
        receipt.append(" \n\n").append(getLineChair(CHAR_EQUALS, limitCharForReceipt)).append("\n" + DataShop.PRICE)
                .append(cartService.getAllPrice(order.getCartList())).append("\n" + DataShop.DISCOUNT_PRODUCT)
                .append(cartService.getDiscountShop(order.getCartList())).append("\n" + DataShop.DISCOUNT_CARD)
                .append(cartService.percentDiscountCard(order.getCartList(), card)).append(" \n")
                .append(getLineChair(CHAR_EQUALS, limitCharForReceipt)).append("\n" + DataShop.TOTAL)
                .append(totalPrice).append(" \n\n ");
        receipt.append(getLineChair(CHAR_SPACE, findMiddle(date, limitCharForReceipt))).append(date).append("\n")
                .append(getLineChair(CHAR_SPACE, findMiddle(DataShop.THANKS, limitCharForReceipt)))
                .append(DataShop.THANKS);
        return receipt.toString();
    }

    public String listItemsReceipt(Order order) {
        StringBuffer receipt = new StringBuffer();
        CartService cartService = new CartService();
        receipt.append("\n");
        for(Cart cart : order.getCartList()) {
            String linePrice = cart.getProduct().getPrice() + "x" + cart.getCount() + "="
                    + cartService.getPriceProduct(cart.getProduct().getPrice(), cart.getCount());
            receipt.append(" \n").append(cart.getProduct().getName());
            receipt.append(getSpace(cart.getProduct().getName(), linePrice, limitCharForReceipt)).append(linePrice);
        }
        return receipt.toString();
    }

    public String listItemsOrder(Order order) {
        StringBuffer receipt = new StringBuffer();
        String headLineOrder = DataShop.BARCODE + getLineChair(CHAR_SPACE,
                                    findMiddle(DataShop.PRODUCT_NAME, limitCharForOrder)) + DataShop.PRODUCT_NAME;
        receipt.append("\n").append(headLineOrder)
                .append(getSpace(headLineOrder, DataShop.COUNT_PRODUCT, limitCharForOrder))
                .append(DataShop.COUNT_PRODUCT).append("\n\n");
        for(Cart cart : order.getCartList()) {
            receipt.append(" \n");
            String lineInfoProduct = cart.getProduct().getBarcode() + getLineChair(CHAR_SPACE, 5) + cart.getProduct().getName();
            String count = "" + cart.getCount();
            receipt.append(lineInfoProduct).append(getSpace(lineInfoProduct, count, limitCharForOrder)).append(count);
        }
        receipt.append("\n\n");
        return receipt.toString();
    }

    public void createFileForOrder(Order order, User user, String nameFile) throws ServiceException {
        StringBuffer orderTextFile = new StringBuffer();
        CardService cardService = new CardService();
        Optional<Card> card = cardService.getCard(order.getIdCardUser());
        orderTextFile.append(DataShop.CLIENT).append(user.getName()).append("\n").append(DataShop.ADDRESS)
                .append(user.getAddress()).append("\n").append(DataShop.RECEIPT_NUMBER).append(nameFile).append("\n")
                .append(DataShop.USE_DISCOUNT_CARD).append(card.map(Card::getIdCard).orElse("No")).append("\n")
                .append(getLineChair(DataShop.CHAR_EQUALS, DataShop.LIMIT_CHAR_FOR_ORDER))
                .append(listItemsOrder(order)).append(getLineChair
                        (DataShop.CHAR_EQUALS, DataShop.LIMIT_CHAR_FOR_ORDER)).append("\n")
                .append(getLineChair(DataShop.CHARS_SPACE, findMiddle
                        (date, DataShop.LIMIT_CHAR_FOR_ORDER))).append(date);
        writeToFile.printToFile(orderTextFile.toString(), nameFile, DataShop.PATH_ORDER);
    }
}
