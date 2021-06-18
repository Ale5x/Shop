package study.shop.model.product;

import java.io.Serializable;

public class Product implements Serializable {

    private long idProduct;
    private String name;
    private boolean discount;
    private double price;
    private Enum category = EProduct.UNKNOWN;

    public Product() {
    }

    public Product(long idProduct, String name, boolean discount, double price) {
        this.idProduct = idProduct;
        this.name = name;
        this.discount = discount;
        this.price = price;
    }

    public Product(long idProduct, String name, boolean discount, double price, Enum category) {
        this.idProduct = idProduct;
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.category = category;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Enum category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", nameProduct='" + name + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
