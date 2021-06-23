package study.shop.service;

import study.shop.model.product.Product;

import java.util.*;

public interface ProductServiceI<T> {

    public List<T> sortByMaxPrice();

    public List<T> sortByMinPrice();

    public List<T> sortByDiscount();

    public List<T> sortByNotDiscount();

    public List<T> sortNameReverse();

    public List<T> sortName();

    public List<T> sortByMaxBarcode();

    public List<T> sortByMinBarcode();

    public Optional findByBarcode(String barcode);

    public List<T> findAllByName(String name);

    public List<T> findAllBetweenPrice(double minPrice, double maxPrice);

    public List<T> showOnlyDiscount();

    public List<T> showOnlyNotDiscount();

    public List<Product> showAll();

    public List<Product> showAllByPage(int page, int countOnPage);
}
