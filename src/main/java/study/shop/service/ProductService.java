package study.shop.service;

import study.shop.io.ReaderData;
import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductService {

    private String pathFile;
    private Enum productCategory = EProduct.UNKNOWN;
    private static final String REGEX_BARCODE = "B[\\w]*";
    private static final String REGEX_PRICE = "P\\d*\\.?\\d*";
    private static final String REGEX_DISCOUNT = "D[\\w]*";
    private static final String REGEX_NAME = "N\\(([^)]*)\\)";


    private List<Product> productList = new ArrayList<>();

    public ProductService() {
        if(productList.isEmpty()) {
            if(Files.exists(Paths.get(getPathFile()))) {
                ReaderData readerData = new ReaderData();
                List<String> linesList = readerData.readAll(getPathFile());

                for (String line : linesList) {
                    if(line.length() > 3) {
                        productList.add(new Product(Long.parseLong(ReaderData.parseData(line, REGEX_BARCODE)),
                                ReaderData.parseData(line, REGEX_NAME),
                                new Boolean(ReaderData.parseData(line, REGEX_DISCOUNT)),
                                Double.parseDouble(ReaderData.parseData(line, REGEX_PRICE)),
                                getCategory()));
                    }
                }
            }
        }
    }

    public List<Product> showByMaxPrice() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getPrice)));
        return productList;
    }

    public List<Product> showByMinPrice() {
        Collections.reverse(showByMaxPrice());
        return productList;
    }

    public List<Product> showByDiscount() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::isDiscount)));
        return productList;
    }
    public List<Product> showByNotDiscount() {
        Collections.reverse(showByDiscount());
        return productList;
    }

    public List<Product> showBySortNameReverse() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getName)));
        return productList;
    }

    public List<Product> showBySortName() {
        Collections.reverse(showBySortNameReverse());
        return productList;
    }

    public List<Product> showByMaxBarcode() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getIdProduct)));
        return productList;
    }

    public List<Product> showByMinBarcode() {
        Collections.reverse(showByMaxBarcode());
        return productList;
    }

    public Product showByBarcode(long barcode) {
        for (Product product : productList) {
            if(product.getIdProduct() == barcode) {
                return product;
            }
        }
        return null;
    }

    public List<Product> showByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(product.equals(product.getName())) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> showByPrice(int min, int max) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> showOnlyDiscount() {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(product.isDiscount()) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> showOnlyNotDiscount() {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(!product.isDiscount()) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> showAll() {
        return productList;
    }

    public List<Product> showAllByPage(int page, int countOnPage) {
        List<Product> result = new ArrayList<>();
        for(int i = page * countOnPage - countOnPage; i < page * countOnPage + countOnPage; i++) {
            result.add(productList.get(i));
        }
        return result;
    }

    public String getPathFile() {
        return pathFile;
    }

    public Enum getCategory() {
        return productCategory;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
