package com.smile.monkeyserver.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static javax.swing.UIManager.getString;

/**
 * @ClassName PoiUtil
 * @Author kris
 * @Date 2019/8/21
 **/
public class PoiUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PoiUtil.class);

    private static final Integer DEFAULT_SHEEET_INDEX = 0;

    public static XSSFWorkbook readData() throws IOException, InvalidFormatException {
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
            System.out.println(sourceSheet.getPhysicalNumberOfRows());
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(titleCell.getStringCellValue(), xssfCell.getStringCellValue());
            }
            list.add(map);
        }
        System.out.println(list);
        SXSSFWorkbook targetWorkbook = new SXSSFWorkbook(xssfWorkbook);

        System.out.println(targetWorkbook);
        FileOutputStream out = new FileOutputStream("C:/Users/yamei/Desktop/wtf.xlsx");
        targetWorkbook.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        targetWorkbook.dispose();
        return xssfWorkbook;
    }

    public static void mergeExcel(String path, String outputPath) throws IOException {
        //1.校验文件是否符合要求
        File targetFile = new File(path);

        if (!targetFile.exists() || !targetFile.isDirectory()) {
            LOGGER.info("文件不存在或者文件不是文件夹");
            return;
        }

        File[] files = targetFile.listFiles(p -> p.getName().endsWith(".xlsx"));
        if (files == null || files.length <= 0) {
            LOGGER.info("文件夹没有excel文件");
            return;
        }

        String startTime = DateUtil.getCurrentTimeStr();
        //2.数据合并
        //目前默认只合并第一个sheet,对于第一个excel文件，所有数据皆写入，对于后面的excel，从第二行数据开始拿起
        int sourceValidRows = 0;
        SXSSFWorkbook srcSXSSWb = null;
        for (int i = 0; i < files.length; i++) {
            if (i == 0) {
                InputStream is = new FileInputStream(files[i]);
                XSSFWorkbook headXssfWb = new XSSFWorkbook(is);
                XSSFSheet sourceSheet = headXssfWb.getSheetAt(DEFAULT_SHEEET_INDEX);
                sourceValidRows = sourceSheet.getPhysicalNumberOfRows();
                //3.写入新文件
                srcSXSSWb = new SXSSFWorkbook(headXssfWb);
            } else {
                InputStream is = new FileInputStream(files[i]);
                XSSFWorkbook targetXssfWb = new XSSFWorkbook(is);
                SXSSFSheet sourceSheet = srcSXSSWb.getSheetAt(DEFAULT_SHEEET_INDEX);
                XSSFSheet targetSheet = targetXssfWb.getSheetAt(DEFAULT_SHEEET_INDEX);
                for (int targetSheetRow = 1; targetSheetRow < targetSheet.getPhysicalNumberOfRows(); targetSheetRow++) {
                    LOGGER.info(i + "--" + targetSheetRow + " | sourceValidRows -- " + (sourceValidRows + targetSheetRow));
                    XSSFRow targetRow = targetSheet.getRow(targetSheetRow);
                    SXSSFRow sourceRow = sourceSheet.createRow(sourceValidRows + targetSheetRow);
                    for (int cellIndex = 0; cellIndex < targetRow.getPhysicalNumberOfCells(); cellIndex++) {
                        XSSFCell targetCell = targetRow.getCell(cellIndex);
                        SXSSFCell sourceCell = sourceRow.createCell(cellIndex);
                        sourceCell.setCellValue(targetCell.getStringCellValue());
                    }
                }
                sourceValidRows = sourceValidRows + targetSheet.getPhysicalNumberOfRows();
                LOGGER.info("有效行数：" + sourceSheet.getPhysicalNumberOfRows());
                is.close();
                targetXssfWb.close();
            }
        }
        //3.写入新文件
        FileOutputStream out = new FileOutputStream(outputPath);
        srcSXSSWb.write(out);
        out.close();

        srcSXSSWb.dispose();
        LOGGER.info("开始时间：" + startTime + ",结束时间：" + DateUtil.getCurrentTimeStr());

    }

    public static void main(String[] args) {
        try {
            mergeExcel("C:/Users/yamei/Desktop/订单管理_20190821141655", "C:/Users/yamei/Desktop/test.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
