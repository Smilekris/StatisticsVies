package com.smile.monkeyserver.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName POIRunnable
 * @Author kris
 * @Date 2019/8/23
 **/
public class POIRunnable implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(POIRunnable.class);
    private static final Integer DEFAULT_SHEEET_INDEX = 0;

    private File file;
    private XSSFWorkbook headXssfWb;
    private Integer sourceValidRows;
    //批次
    private Integer index;

    public POIRunnable(File file, XSSFWorkbook headXssfWb, Integer sourceValidRows, Integer index) {
        this.file = file;
        this.headXssfWb = headXssfWb;
        this.sourceValidRows = sourceValidRows;
        this.index = index;
    }
//    ExecutorService executorService = new ThreadPoolExecutor(2,4,2, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1000));
//                executorService.execute(new POIRunnable(files[i],headXssfWb,sourceValidRows,i));
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XSSFWorkbook targetXssfWb = new XSSFWorkbook(is);
            XSSFSheet sourceSheet = headXssfWb.getSheetAt(DEFAULT_SHEEET_INDEX);
            XSSFSheet targetSheet = targetXssfWb.getSheetAt(DEFAULT_SHEEET_INDEX);
            sourceValidRows = (index - 1) > 0 ? sourceValidRows + targetSheet.getPhysicalNumberOfRows() : sourceValidRows;
            for (int targetSheetRow = 1; targetSheetRow < targetSheet.getPhysicalNumberOfRows(); targetSheetRow++) {
                System.out.println(index + "--" + targetSheetRow+" | sourceValidRows -- "+sourceValidRows);
                XSSFRow targetRow = targetSheet.getRow(targetSheetRow);
                XSSFRow sourceRow = sourceSheet.createRow(sourceValidRows + targetSheetRow);
                for (int cellIndex = 0; cellIndex < targetRow.getPhysicalNumberOfCells(); cellIndex++) {
                    XSSFCell targetCell = targetRow.getCell(cellIndex);
                    XSSFCell sourceCell = sourceRow.createCell(cellIndex);
                    sourceCell.setCellValue(targetCell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            LOGGER.error("POIRunnable error",e);
        }

    }
}
