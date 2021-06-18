package study.shop.io;

import study.shop.model.product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriterData {

    public void write(Product product, String path) {
        try {
            String writeData = getProductString(product);
            if(Files.exists(Paths.get(path))) {
                Files.write(Paths.get(path), "\n".getBytes(), StandardOpenOption.APPEND);
                Files.write(Paths.get(path), writeData.getBytes(), StandardOpenOption.APPEND);
            }
        }catch (IOException e) {

        }

    }

    public void printReceipt(String receipt, String nameReceipt, String pathReceipt) {
        try{
            String pathReceiptString = pathReceipt + nameReceipt;
            Path pathReceiptPath = Paths.get(pathReceiptString + ".txt");
            Files.createFile(pathReceiptPath);
            Files.write(pathReceiptPath, receipt.getBytes());

        }catch (IOException e) {}

    }

    public String getProductString(Product product) {
        String step = "   ";
        return "B" + product.getIdProduct() + step + "N(" + product.getName() + ")" + step
                + "D" + product.isDiscount() + step + "P" + product.getPrice();
    }
}
