package study.shop.io;

import org.junit.Before;
import org.junit.Test;

import study.shop.exception.ServiceException;

import java.util.List;

import static org.junit.Assert.*;

public class ReadDataFromFileWTest {

    ReadDataFromFileW readDataFromFileW;

    @Before
    public void setUp() {
        readDataFromFileW = new ReadDataFromFileW();
    }

    @Test
    public void readAll() throws ServiceException {
        String pathTestProduct = "src/test/resources/Test/Product/Test products.txt";
        List<String> lineList = readDataFromFileW.readAll(pathTestProduct);
        assertFalse(lineList.isEmpty());
    }

    @Test
    public void parseData() throws ServiceException{
        String regexBarcode = "B[\\w]*";
        String word = "B2158964";
        int expected = word.length() - 1;
        int actual = ReadDataFromFileW.parseData(word, regexBarcode).length();
        assertEquals(expected, actual );
    }
}