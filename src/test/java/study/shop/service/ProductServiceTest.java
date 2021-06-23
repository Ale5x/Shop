package study.shop.service;

import org.junit.Before;
import org.junit.Test;
import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductServiceTest {

    ProductService productService;
    private List<Product> productTestList;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(EProduct.TEST);
    }

    @Test
    public void sortByMaxPrice() {
        List<Product> resultList = productService.sortByMaxPrice();
        assertTrue(resultList.get(0).getPrice() >= resultList.get(1).getPrice());
    }

    @Test
    public void sortByMinPrice() {
        List<Product> resultList = productService.sortByMinPrice();
        assertTrue(resultList.get(0).getPrice() <= resultList.get(1).getPrice());
    }

    @Test
    public void sortByDiscount() {
        List<Product> resultList = productService.sortByDiscount();
        assertTrue(resultList.get(0).isDiscount());
    }

    @Test
    public void sortByNotDiscount() {
        List<Product> resultList = productService.sortByNotDiscount();
        assertFalse(resultList.get(0).isDiscount());
    }

    @Test
    public void sortNameReverse() {
    }

    @Test
    public void sortName() {
    }

    @Test
    public void sortByMaxBarcode() {
        String expected = "999999999";
        List<Product> resultList = productService.sortByMaxBarcode();
        String actual = resultList.get(0).getBarcode();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void sortByMinBarcode() {
        String expected = "1000000012";
        List<Product> resultList = productService.sortByMinBarcode();
        String actual = resultList.get(0).getBarcode();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void findByBarcode() {
//        B4  N(Car 7)	Dfalse	P7.0
        String barcode = "4";
        Optional<Product> optional = productService.findByBarcode(barcode);
        String expected = "4";
        String actual = optional.map(Product::getBarcode).orElse("0");
        assertTrue(expected.equals(actual));
    }

    @Test
    public void findByNotCorrectBarcode() {
//        B4  N(Car 7)	Dfalse	P7.0
        String barcode = "000123";
        Optional<Product> optional = productService.findByBarcode(barcode);
        String expected = "0";
        String actual = optional.map(Product::getBarcode).orElse("0");
        assertEquals(expected, actual);
    }

    @Test
    public void findAllByName() {
        String name = "car 7";
        List<Product> resultList = productService.findAllByName(name);
        assertFalse(resultList.isEmpty());
    }

    @Test
    public void findAllBetweenPrice() {
        double minPrice = 5;
        double maxPrice = 20;
        List<Product> resultList = productService.findAllBetweenPrice(minPrice, maxPrice);
        boolean flag = true;
        for(Product product : resultList) {
            if(product.getPrice() > maxPrice && product.getPrice() < maxPrice) {
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void showOnlyDiscount() {
        List<Product> resultList = productService.showOnlyDiscount();
        boolean flag = true;
        for(Product product : resultList) {
            if(!product.isDiscount()) {
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void showOnlyNotDiscount() {
        List<Product> resultList = productService.showOnlyNotDiscount();
        boolean flag = true;
        for(Product product : resultList) {
            if(product.isDiscount()) {
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void showAll() {
        List<Product> listResult = productService.showAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void showAllByPage() {
        int page = 2;
        int count = 5;
        List<Product> resultList = productService.showAllByPage(page, count);
        long expected = page * count;
        long actual = resultList.get(count - 1).getIdProduct();
        assertEquals(expected, actual);
    }
}