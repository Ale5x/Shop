package study.shop.service;

import org.junit.Before;
import org.junit.Test;
import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {

    ProductService productService;
    private String pathTestProduct = "Data shop/Test/Product/Test products.txt";
    private List<Product> productList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        productService = new ProductService();
    }

    @Test
    public void showByMaxPrice() {
        productList = productService.showByMaxPrice();
        for(Product product : productList) {
            System.out.println(product.toString());
        }
        assertTrue(productList.get(1).getPrice() >= productList.get(2).getPrice());
    }

    @Test
    public void showByMinPrice() {
        List<Product> listResult;
        listResult = productService.showByMinPrice();
        for(Product product : listResult) {
            System.out.println(product.toString());
        }
        assertTrue(listResult.get(1).getPrice() <= listResult.get(2).getPrice());
    }

    @Test
    public void showByDiscount() {
        List<Product> listResult;
        listResult = productService.showByDiscount();
        for(Product product : listResult) {
            System.out.println(product.toString());
        }
        assertTrue(listResult.get(1).isDiscount());
    }

    @Test
    public void showByNotDiscount() {
        List<Product> listResult;
        listResult = productService.showByNotDiscount();
        for(Product product : listResult) {
            System.out.println(product.toString());
        }
        assertFalse(listResult.get(1).isDiscount());
    }

    @Test
    public void showBySortNameReverse() {
    }

    @Test
    public void showBySortName() {
    }

    @Test
    public void showByMaxBarcode() {
    }

    @Test
    public void showByMinBarcode() {
    }

    @Test
    public void getByBarcode() {
    }

    @Test
    public void getByName() {
    }

    @Test
    public void getByPrice() {
    }

    @Test
    public void getByDiscount() {
    }

    @Test
    public void getByNotDiscount() {
    }

    @Test
    public void showAll() {
        productService.setPathFile(pathTestProduct);
        List<Product> listResult;
        listResult = productService.showAll();
        for(Product product : listResult) {
            System.out.println(product.toString());
        }
        System.out.println("Size - " + listResult.size());
        assertTrue(listResult.isEmpty());
    }

    @Test
    public void showAllByPage() {
    }

    @Test
    public void setPathFile() {
        productService.setPathFile(pathTestProduct);
    }


    public String getPathFile() {
        return pathTestProduct;
    }

    public Enum getCategory() {
        return EProduct.CHILDREN;
    }
}