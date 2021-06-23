package study.shop.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;
import study.shop.model.product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteDataInFileW implements WriteDataInFileI {

    private static final Logger logger = LogManager.getLogger(WriteDataInFileW.class);

    @Override
    public void addProductToFile(Product product, String path) throws ServiceException {
        try {
            String writeData = getProductString(product);
            Files.write(Paths.get(path), "\n".getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(path), writeData.getBytes(), StandardOpenOption.APPEND);

        }catch (IOException e) {
            logger.warn(DataShop.ERROR_FILE_NOT_ADD + DataShop.ERROR_PATH_FILE + path, e);
            throw new ServiceException(e);
        }

    }

    @Override
    public void printToFile(String text, String nameFile, String pathFile) throws ServiceException {
        try {
            String pathReceiptString = pathFile + nameFile;
            Path pathReceiptPath = Paths.get(pathReceiptString + ".txt");
            Files.createFile(pathReceiptPath);
            Files.write(pathReceiptPath, text.getBytes());
        }catch (IOException e) {
            logger.warn(DataShop.ERROR_DIR_NOT_EXIST + DataShop.ERROR_PATH_FILE + pathFile, e);
            throw new ServiceException(e);
        }
    }

    @Override
    public String getProductString(Product product) {
        String step = "   ";
        return "B" + product.getBarcode() + step + "N(" + product.getName() + ")" + step
                + "D" + product.isDiscount() + step + "P" + product.getPrice();
    }
}
