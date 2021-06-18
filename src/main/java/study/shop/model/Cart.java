package study.shop.model;

import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Cart {

    private DecimalFormatSymbols locale = new DecimalFormatSymbols(Locale.ENGLISH);
    private DecimalFormat formatPrice = new DecimalFormat("#.##", locale);

    private Product product;
    private int count;

    public Cart() {

    }

    public Cart(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return product.getName() + "    " + product.getPrice() +"x" + getCount() + "----" + product.getCategory();
    }
    //    private List<Product> cartList = new ArrayList<>();
//
//    public List<Cart> add(Product product, int count) {
//        List<Cart> cartList = new ArrayList<>();
//       // cartList.add(product, count);
//        return cartList;
//    }
//
//    public List<Product> delete(List<Product> cartList, long idProduct) {
//        if(check(cartList, idProduct)) {
//            for(Product product : cartList) {
//                if(product.getIdProduct() == idProduct) {
//                    cartList.remove(idProduct);
//                }
//            }
//        }
//        return cartList;
//    }
//
//    public boolean check(List<Product> cartList, long idProduct) {
//        for(Product product : cartList) {
//            if(product.getIdProduct() == idProduct) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public double getPrice(List<Product> listCustomerProduct) {
//        double price = 0;
//        for(Product product : listCustomerProduct) {
//            price = price + (product.getPrice() * product.getCount());
//        }
//        return price;
//    }
//
//    public double getDiscountProduct(List<Product> listCustomerProduct) {
//        double countDiscountProduct = 0;
//        double discountPrice = 0;
//        for(Product product : listCustomerProduct) {
//            if(product.isDiscount()) {
//                discountPrice += product.getPrice() * product.getCount() * 0.1;
//                countDiscountProduct = countDiscountProduct + 1 * product.getCount();
//            }
//        }
//        if(countDiscountProduct >= 5) {
//            return Double.parseDouble(formatPrice.format(discountPrice));
//        }
//        return 0;
//    }
//
//    public double getTotalPrice(List<Product> listCustomerProduct, double percentDiscountCard) {
//        return getPrice(listCustomerProduct) - getDiscountProduct(listCustomerProduct) - percentDiscountCard;
//    }
//
//    public double percentDiscountCard(List<Product> listCustomerProduct) {
//        return getPrice(listCustomerProduct) * card.getPercentCard();
//    }
}
