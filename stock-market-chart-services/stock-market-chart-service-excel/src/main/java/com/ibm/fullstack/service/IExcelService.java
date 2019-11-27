package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.StockPriceDetail;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IExcelService {

    public void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response);

    public void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response);

    public void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response);

    public <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass);

    public <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass);

    public StockPriceDetail uploadExcelToMysql(StockPriceDetail stockPriceDetail);
}
