package study.shop.io;

import study.shop.exception.ServiceException;
import study.shop.model.product.Product;


public interface WriteDataInFileI {

    public void addProductToFile(Product product, String path) throws ServiceException;

    public void printToFile(String text, String nameReceipt, String pathReceipt) throws ServiceException;

    public String getProductString(Product product);

}
