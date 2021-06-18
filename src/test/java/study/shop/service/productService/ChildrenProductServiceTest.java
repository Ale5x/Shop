package study.shop.service.productService;

import org.junit.Before;
import org.junit.Test;
import study.shop.constant.DataShop;
import study.shop.model.product.Product;
import study.shop.service.ProductService;

import java.util.List;

import static org.junit.Assert.*;

public class ChildrenProductServiceTest {

    ProductService productService;
    List<Product> productList;


    @Before
    public void setUp() throws Exception {
        productService = new ChildrenProductService();

    }

    @Test
    public void showByMaxPrice() {
        productList = productService.showByMaxPrice();
        output();
        assertTrue(productList.get(1).getPrice() >= productList.get(2).getPrice());
    }

    @Test
    public void showByMinPrice() {
        productList = productService.showByMinPrice();
        output();
        assertTrue(productList.get(1).getPrice() <= productList.get(2).getPrice());
    }

    @Test
    public void showByDiscount() {
        productList = productService.showByDiscount();
        output();
        assertTrue(productList.get(1).isDiscount());
    }

    @Test
    public void showByNotDiscount() {
        productList = productService.showByNotDiscount();
        output();
        assertFalse(productList.get(1).isDiscount());
    }

    @Test
    public void showBySortNameReverse() {

    }

    @Test
    public void showBySortName() {
    }

    @Test
    public void showByMaxBarcode() {
        productList = productService.showByMaxBarcode();
        output();
        assertTrue(productList.get(1).getIdProduct() >= productList.get(2).getIdProduct());
    }

    @Test
    public void showByMinBarcode() {
        productList = productService.showByMinBarcode();
        output();
        assertTrue(productList.get(1).getIdProduct() <= productList.get(2).getIdProduct());
    }

    @Test
    public void getByBarcode() {
        Product expected = new Product(1l, "Toy", true, 5.5);
        long barcode = 1;
        Product actual = productService.showByBarcode(barcode);

        assertEquals(expected, actual);
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


    private void output() {
        for(Product product : productList) {
            System.out.println(product.toString());
        }
    }
}