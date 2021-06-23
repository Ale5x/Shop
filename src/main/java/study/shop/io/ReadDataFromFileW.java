package study.shop.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import study.shop.constant.DataShop;
import study.shop.exception.ServiceException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadDataFromFileW implements ReadDataFromFileI{

    private static final Logger logger = LogManager.getLogger(ReadDataFromFileW.class);

    @Override
    public List<String> readAll(String filePath) throws ServiceException {
        List<String> lines = new ArrayList<>();
        try(FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            lines = bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(DataShop.ERROR_FILE_NOT_FIND + DataShop.ERROR_PATH_FILE + filePath, e);
            throw new ServiceException(e);
        }
        return lines;
    }


    public static String parseData(String line, String regex) throws ServiceException {
        Matcher matcher = Pattern.compile(regex).matcher(line);
        StringBuffer strBuffer = new StringBuffer();
        while (matcher.find()) {
            strBuffer.append(matcher.group(0));
        }
        if(strBuffer.charAt(1) == '(') {
            return removeBrackets(strBuffer);
        }
        return strBuffer.deleteCharAt(0).toString();
    }

    private static String removeBrackets(StringBuffer stringBuffer) {
        for(int i = 0; i < 2; i++) {
            stringBuffer.deleteCharAt(0);
        }
        return stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
    }
}
