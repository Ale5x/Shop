package study.shop.io;

import org.junit.Before;
import org.junit.Test;
import study.shop.model.product.Product;

import java.util.List;

import static org.junit.Assert.*;

public class ReaderDataTest {

    ReaderData readerData;

    @Before
    public void setUp() throws Exception {
        readerData = new ReaderData();
    }

    @Test
    public void readAll() {
        String pathTestProduct = "src/test/resources/Test/Product/Test products.txt";
        List<String> lineList = readerData.readAll(pathTestProduct);
        assertFalse(lineList.isEmpty());
    }

    @Test
    public void parseData() {
        String regexBarcode = "B[\\w]*";
        String word = "B2158964";
        int expected = word.length() - 1;
        int actual = ReaderData.parseData(word, regexBarcode).length();
        assertEquals(expected, actual );
    }
}