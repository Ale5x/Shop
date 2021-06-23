package study.shop.model;

import java.util.List;

public class Order {


    private String idCardUser;
    private List<Cart> cartList;

    public Order() {
    }

    public Order(List<Cart> cartList, String idCardUser) {
        this.cartList = cartList;
        this.idCardUser = idCardUser;
    }

    public String getIdCardUser() {
        return idCardUser;
    }

    public List<Cart> getCartList() {
        return cartList;
    }


}
