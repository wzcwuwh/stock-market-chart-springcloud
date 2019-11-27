package com.ibm.fullstack.util;

import com.ibm.fullstack.dto.ExcelStockData;
import com.ibm.fullstack.entity.StockPriceDetail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelDataToMysqlData {

    public static StockPriceDetail transform(ExcelStockData esd){
        StockPriceDetail spd = new StockPriceDetail();
        spd.setCompanyCode(esd.getCompanyCode());
        spd.setStockExchange(esd.getStockExchange());
        spd.setCurrentPrice(new BigDecimal(esd.getCurrentPrice()));

        String normalDateStr = excelDateToNormalFormat(esd.get_date());
        java.sql.Date mysqlDate = strToDate(normalDateStr);
        spd.set_date(mysqlDate);

        spd.set_time(strToTime(esd.get_time()));
        return spd;
    }

    private static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    private static java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time;
    }

    private static String excelDateToNormalFormat(String excelDate){
        Date time =new Date(excelDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }
}
