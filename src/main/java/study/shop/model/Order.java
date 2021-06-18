package study.shop.model;

import study.shop.constant.DataShop;
import study.shop.io.ReaderData;
import study.shop.service.CardService;
import study.shop.service.CartService;

import java.util.GregorianCalendar;
import java.util.List;

public class Order<T> {

    private CardService cardService = new CardService();
    private CartService cartService = new CartService();
    private ReaderData readerData = new ReaderData();

    private int idReceipt = 1;
    private int count;
    private long idCardUser;

  //  private Card card;
    private List<Cart> cartList;

    public Order() {
    }

    public Order(List<Cart> cartList, long idCardUser) {
        this.cartList = cartList;
        this.idCardUser = idCardUser;
    }

    public long getIdCardUser() {
        return idCardUser;
    }

//    public Card getCard() {
//        return card;
//    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public String orderPayment(Order order) {
        Card card = cardService.getCard(order.getIdCardUser());
        double percentDiscountCard = 0;
        int randomNumber = getRandomNumber();
        StringBuilder receipt = new StringBuilder();
        String receiptID = "" + randomNumber;

        receipt.append(DataShop.CHARS_SPACE).append(DataShop.NAME_SHOP).append("\n").
                append(DataShop.CHARS_HALF_SPACE).append(DataShop.ADDRESS_SHOP).
                append(" \n").append(DataShop.CHARS_EQUALS).append("\n").append(DataShop.CHARS_SPACE).
                append(DataShop.RECEIPT_NUMBER).append(idReceipt++).append("-").
                append(receiptID).append("\n").append(DataShop.CHARS_EQUALS).
                append("\n" + DataShop.USE_DISCOUNT_CARD);
        if(!(order.getIdCardUser() == 0)) {
            if (cardService.isDiscount(order.getIdCardUser())) {
              //  percentDiscountCard = card.getPercent();
                receipt.append(card.getIdCard());
            }else receipt.append(DataShop.NO);
        }
        receipt.append(" \n").append(DataShop.CHARS_EQUALS);
        for(Cart cart : getCartList()) {
            String linePrice = cart.getProduct().getPrice() + "x" + cart.getCount() + "="
                    + cartService.getPriceProduct(cart.getProduct().getPrice(), cart.getCount());
            receipt.append(" \n").append(cart.getProduct().getName()).
                    append(getSpace(cart.getProduct().getName(), linePrice)).append(linePrice);
//            receipt.append(" \n").append(cart.getProduct().getName()).append("   ").
//                    append(cart.getProduct().getPrice()).append("x").append(cart.getCount()).append("=").
//                    append(cart.getProduct().getPrice() * cart.getCount());

        }
        double totalPrice = cartService.getTotalPrice(getCartList(), card);
        receipt.append(" \n").append(DataShop.CHARS_EQUALS).append("\n" + DataShop.PRICE).
                append(cartService.getAllPrice(getCartList())).append("\n" + DataShop.DISCOUNT_PRODUCT).
                append(cartService.getDiscountShop(getCartList())).append("\n" + DataShop.DISCOUNT_CARD).
                append(cartService.percentDiscountCard(getCartList(), card)).append(" \n").
                append(DataShop.CHARS_EQUALS).append("\n" + DataShop.TOTAL).append(totalPrice).append(" \n\n ").
                append(new GregorianCalendar().getTime()).append("\n" + DataShop.THANKS);
        //readerData.printReceipt(receipt.toString(), receiptID);

        return receipt.toString();
    }

    private int getRandomNumber() {
        return (int)(Math.random() * 10000);
    }

    private String getSpace(String productName, String linePrice) {
        int limit = 50;
        StringBuffer countSpaceLine = new StringBuffer();
        int countSpace = limit - productName.length() - linePrice.length();
        if(countSpace >= 0) {
            for (int i = 0; i < countSpace; i++) {
                countSpaceLine.append(" ");
            }
        }
        return countSpaceLine.toString();
    }

}
