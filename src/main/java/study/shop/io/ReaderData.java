package study.shop.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReaderData {


    private Path pathReceipt;

    public List<String> readAll(String filePath) {
        List<String> lines = new ArrayList<>();
        try(FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            lines = bufferedReader.lines()
                                  .collect(Collectors.toList());
        } catch (IOException e) {

        }
        return lines;
    }

    public void printReceipt(String receipt, String nameReceipt) {
        try{
                String pathReceiptString = "src/Data shop/Print receipt/" + nameReceipt;
                pathReceipt = Paths.get(pathReceiptString + ".txt");
                Files.createFile(pathReceipt);
                Files.write(pathReceipt, receipt.getBytes());

        }catch (IOException e) {}

    }

    public static String parseData(String line, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(line);
        StringBuffer strBuffer = new StringBuffer();
        while (matcher.find()) {
            strBuffer.append(matcher.group(0));
        }
        return strBuffer.deleteCharAt(0).toString();
    }
}
