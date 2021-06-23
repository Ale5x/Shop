package study.shop.io;

import org.junit.Before;
import org.junit.Test;
import study.shop.exception.ServiceException;
import study.shop.model.product.Product;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class WriteDataInFileWTest {

    WriteDataInFileW writeDataInFileW;

    @Before
    public void setUp() throws Exception {
        writeDataInFileW = new WriteDataInFileW();
    }

    @Test
    public void write() {
//        String pathTestProduct = "src/test/resources/Test/Test write products.txt";
//        Product product = new Product(1l, "Name test", false, 7.7, EProduct.UNKNOWN);
//        writeDataInFileW.write(product, pathTestProduct);
//        assertTrue(Files.exists(Paths.get(pathTestProduct)));

    }

    @Test
    public void printReceipt() throws ServiceException {
        String receiptPath = "src/test/resources/Test/";
        String receipt = "Test receipt record";
        String nameReceipt = "Test receipt record";
        String resultPath = receiptPath + nameReceipt + ".txt";
        writeDataInFileW.printToFile(receipt, nameReceipt, receiptPath);
        assertTrue(Files.exists(Paths.get(resultPath)));
    }

    @Test
    public void getProductString() {
        Product product = new Product(0, "1", "name", true, 1.0);
        String step = "   ";
        String expected = "B1" + step + "N(name)" + step +  "Dtrue" + step + "P1.0";
        String actual = writeDataInFileW.getProductString(product);
        assertEquals(expected, actual);
    }
}