package com.smile.monkeyserver.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName PoiUtil
 * @Author kris
 * @Date 2019/8/21
 **/
public class PoiUtil {
    public void readData() throws IOException, InvalidFormatException {
//        InputStream ins = null;
//        ins = new FileInputStream(new File(file));
//        //ins= ExcelService.class.getClassLoader().getResourceAsStream(filePath);
//        Workbook wb = WorkbookFactory.create(ins);
//        ins.close();
    }
}
