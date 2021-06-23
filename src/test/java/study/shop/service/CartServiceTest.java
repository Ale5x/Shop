package study.shop.service;

import org.junit.Before;
import org.junit.Test;
import study.shop.model.Card;
import study.shop.model.Cart;
import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartServiceTest {

    CartService cartService;
    List<Cart> cartList = new ArrayList<>();
    List<Cart> result = new ArrayList<>();
//
//    @Before
//    public void init() {
//        cartService = new CartService();
//        cartList.add(
//                new Cart(new Product(12345, "Pie", true, 5, EProduct.FOOD), 3));
//        cartList.add(
//                new Cart(new Product(67890, "Toy", false, 10, EProduct.CHILDREN), 1));
//    }
//
//    @Test
//    public void addProduct() {
//
//        Product product = new Product(12345, "Butter", true, 5, EProduct.FOOD);
//        int countProduct = 1;
//        int expected = cartList.size() + 1;
//        result = cartService.addProduct(cartList, product, countProduct);
//        int actual = result.size();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void remove() {
//        int numberDeleteProduct = 2;
//        int expected = cartList.size();
//        result = cartService.remove(cartList, numberDeleteProduct);
//        int actual = result.size();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void getAllPrice() {
//        double expected = 25.0;
//        double actual = cartService.getAllPrice(cartList);
//        assertTrue(expected == actual);
//    }
//
//    @Test
//    public void getPriceProduct() {
//        double priceProduct = 10.0;
//        int count = 1;
//        double expected = 10.0;
//        double actual = cartService.getPriceProduct(priceProduct, count);
//        assertTrue(expected == actual);
//    }
//
//    @Test
//    public void getDiscountShop() {
//        cartList.add(
//                new Cart(new Product(12345, "Pie", true, 5, EProduct.FOOD), 4));
//        double expected = 1.75;
//        double actual = cartService.getDiscountShop(cartList);
////        System.out.println("expected - " + expected + " actual - " + actual);
////        for(Cart cart : cartList) {
////            System.out.println(cart);
////        }
//        assertTrue(expected == actual);
//    }
//
//    @Test
//    public void getTotalPrice() {
//
////        for(Cart cart : cartList) {
////            System.out.println(cart);
////        }
//
//        double expected = 25.0;
//        Card card = new Card(001, 0);
//        double actual = cartService.getTotalPrice(cartList, card);
//        assertTrue(expected == actual);
//    }
//
//    @Test
//    public void percentDiscountCard() {
//        double expected = 2.5;
//        Card card = new Card(001, 10);
//        double actual = cartService.percentDiscountCard(cartList, card);
//        System.out.println("expected - " + expected + " actual - " + actual);
//        assertTrue(expected == actual);
//    }
}