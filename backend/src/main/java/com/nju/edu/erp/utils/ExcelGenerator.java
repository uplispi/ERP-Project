package com.nju.edu.erp.utils;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelGenerator {
    public static SXSSFWorkbook getSXSSFWorkbook(String[] title, String[][] values) {
        //契约式编程
        //表头不能为空
        if (title == null){
            throw new RuntimeException("表头不能为空");
        }

        //检查列数一致
        if (values != null && values.length > 0 && values[0] != null && values[0].length != title.length){
            throw new RuntimeException("内容应与表头列数一致");
        }

        //便于测试
        System.out.println("title");
        System.out.println(Arrays.toString(title));
        System.out.println("values");
        for (String[] value : values) {
            System.out.println(Arrays.toString(value));
        }


        // 创建表格
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);

        //设置表格名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SXSSFSheet sheet = wb.createSheet(sdf.format(new Date()).toString());

        // 设置默认的宽度为30个字符
        sheet.setDefaultColumnWidth(30);

        //添加表头
        SXSSFRow row = sheet.createRow(0);
        int i = 0;
        for (String s : title) {
            row.createCell(i).setCellValue(s);
            i++;
        }

        //添加表格内容
        i = 0;
        int j = 0;
        for (String[] value : values) {
            j = 0;
            row = sheet.createRow(i+1);
            for (String s : value) {
                row.createCell(j).setCellValue(s);
                j++;
            }
            i++;
        }

        return wb;
    }

    /**
     * 防止null导致的toString错误
     * @param obj
     * @return
     */
    public static String getString(Object obj){
        return obj == null?"":obj.toString();
    }
}