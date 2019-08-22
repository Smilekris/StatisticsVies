package com.smile.monkeyserver.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.getString;

/**
 * @ClassName PoiUtil
 * @Author kris
 * @Date 2019/8/21
 **/
public class PoiUtil {
    public static void readData() throws IOException, InvalidFormatException {
        InputStream is = new FileInputStream(new File("C:/Users/yamei/Desktop/订单管理_20190821141655/订单管理_20190821141636_1.xlsx"));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        //第一个sheet
        XSSFSheet sourceSheet = xssfWorkbook.getSheetAt(0);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        //定义行
        //默认第一行为标题行，index = 0
        XSSFRow titleRow = sourceSheet.getRow(0);

        //循环取每行的数据
        for (int rowIndex = 1; rowIndex < sourceSheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow xssfRow = sourceSheet.getRow(rowIndex);
            if (xssfRow == null) {
                continue;
            }

            Map<String, String> map = new LinkedHashMap<String, String>();
            //循环取每个单元格(cell)的数据
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(titleCell.getStringCellValue(),xssfCell.getStringCellValue());
            }
            list.add(map);
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
