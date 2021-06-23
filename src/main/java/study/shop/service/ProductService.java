package study.shop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;
import study.shop.io.ReadDataFromFileW;
import study.shop.model.product.EProduct;
import study.shop.model.product.Product;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProductService implements ProductServiceI<Product> {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private static final String REGEX_BARCODE = "B[\\w]*";
    private static final String REGEX_PRICE = "P\\d*\\.?\\d*";
    private static final String REGEX_DISCOUNT = "D[\\w]*";
    private static final String REGEX_NAME = "N\\(([^)]*)\\)";


    private List<Product> productList = new ArrayList<>();

    public ProductService(EProduct eProduct) throws ServiceException {
        try {
            //if(Files.exists(Paths.get(eProduct.getPathProduct()))) {
                ReadDataFromFileW readDataFromFileW = new ReadDataFromFileW();
                List<String> linesList = readDataFromFileW.readAll(eProduct.getPathProduct());

                long idProduct = 1;
                for (String line : linesList) {
                    if(line.length() > 3) {
                        productList.add(new Product(idProduct, ReadDataFromFileW.parseData(line, REGEX_BARCODE),
                                ReadDataFromFileW.parseData(line, REGEX_NAME),
                                new Boolean(ReadDataFromFileW.parseData(line, REGEX_DISCOUNT)),
                                Double.parseDouble(ReadDataFromFileW.parseData(line, REGEX_PRICE)),
                                eProduct.getCategory()));
                        idProduct++;
                    }
                }
           // }
        }catch (ServiceException e) {
            logger.error(DataShop.ERROR_PRODUCT_NOT_INIT + DataShop.CATEGORY + eProduct.getCategory()
                        + " " + DataShop.ERROR_PATH_FILE + eProduct.getPathProduct(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> sortByMaxPrice() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getPrice)));
        return productList;
    }

    @Override
    public List<Product> sortByMinPrice() {
        Collections.reverse(sortByMaxPrice());
        return productList;
    }

    @Override
    public List<Product> sortByDiscount() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::isDiscount)));
        return productList;
    }
    @Override
    public List<Product> sortByNotDiscount() {
        Collections.reverse(sortByDiscount());
        return productList;
    }

    @Override
    public List<Product> sortNameReverse() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getName)));
        return productList;
    }

    @Override
    public List<Product> sortName() {
        Collections.reverse(sortNameReverse());
        return productList;
    }

    @Override
    public List<Product> sortByMaxBarcode() {
        Collections.sort(productList, Collections.reverseOrder(Comparator.comparing(Product::getBarcode)));
        return productList;
    }

    @Override
    public List<Product> sortByMinBarcode() {
        Collections.reverse(sortByMaxBarcode());
        return productList;
    }

    @Override
    public Optional findByBarcode(String barcode) {
        for (Product product : productList) {
            if(product.getBarcode().equals(barcode)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAllByName(String name) {
        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if(product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> findAllBetweenPrice(double minPrice, double maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> showOnlyDiscount() {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(product.isDiscount()) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> showOnlyNotDiscount() {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if(!product.isDiscount()) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> showAll() {
        return productList;
    }

    @Override
    public List<Product> showAllByPage(int page, int countOnPage) {
        List<Product> result = new ArrayList<>();
        for(int i = page * countOnPage - countOnPage; i < page * countOnPage; i++) {
            result.add(productList.get(i));
        }
        return result;
    }
}
