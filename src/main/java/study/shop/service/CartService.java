package study.shop.service;

import study.shop.constant.DataShop;
import study.shop.model.Cart;
import study.shop.model.Card;
import study.shop.model.product.Product;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class CartService {

    private DecimalFormatSymbols locale = new DecimalFormatSymbols(Locale.ENGLISH);
    private DecimalFormat formatPrice = new DecimalFormat("#.##", locale);


    public List<Cart> addProduct(List<Cart> cartList, Product product, int count) {
       cartList.add(new Cart(product, count));
       return cartList;
    }

    public List<Cart> remove(List<Cart> cartList, long number) {
        cartList.remove(number);
        return cartList;
    }

    public double getAllPrice(List<Cart> listCustomerProduct) {
        double price = 0;
        for(Cart cart : listCustomerProduct) {
            price = price + (cart.getProduct().getPrice() * cart.getCount());
        }
        return Double.parseDouble(formatPrice.format(price));
    }

    public double getPriceProduct(double price, int count) {
        return Double.parseDouble(formatPrice.format(price * count));
    }

    public double getDiscountShop(List<Cart> listCustomerProduct) {
        double countDiscountProduct = 0;
        double discountPrice = 0;
        for(Cart cart : listCustomerProduct) {
            if(cart.getProduct().isDiscount()) {
                discountPrice += cart.getProduct().getPrice() * cart.getCount() * 0.01
                        * DataShop.PERCENT_DISCOUNT_SHOP_DEFAULT;
                countDiscountProduct = countDiscountProduct + 1 * cart.getCount();
            }
        }
        if(countDiscountProduct >= DataShop.COUNT_DISCOUNT_DEFAULT) {
            return Double.parseDouble(formatPrice.format(discountPrice));
        }
        return 0;
    }

    public double getTotalPrice(List<Cart> listCustomerProduct, Card percentDiscount) {
        return Double.parseDouble(formatPrice.format(getAllPrice(listCustomerProduct)
                - getDiscountShop(listCustomerProduct) - percentDiscount.getPercent()));
    }

    public double percentDiscountCard(List<Cart> listCustomerProduct, Card card) {
        return Double.parseDouble(formatPrice.format(getAllPrice(listCustomerProduct) * 0.01
                * card.getPercent()));
    }
}
