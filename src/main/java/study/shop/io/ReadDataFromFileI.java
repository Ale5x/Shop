package study.shop.io;

import study.shop.exception.ServiceException;

import java.util.List;

public interface ReadDataFromFileI {

    public List<String> readAll(String filePath) throws ServiceException;

}
