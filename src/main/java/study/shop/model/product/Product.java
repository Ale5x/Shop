package study.shop.model.product;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private static final long serialVersionUID = 6196420431799573710L;

    private long idProduct;
    private String barcode;
    private String name;
    private boolean discount;
    private double price;
    private String category;

    public Product() {
    }

    public Product(long idProduct, String barcode, String name, boolean discount, double price) {
        this.idProduct = idProduct;
        this.barcode = barcode;
        this.name = name;
        this.discount = discount;
        this.price = price;
    }

    public Product(String barcode, String name, double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public Product(long idProduct, String barcode, String name, boolean discount, double price, String category) {
        this.idProduct = idProduct;
        this.barcode = barcode;
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return barcode == product.barcode &&
                discount == product.discount &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, name, discount, price, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", barcode=" + barcode +
                ", nameProduct='" + name + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
