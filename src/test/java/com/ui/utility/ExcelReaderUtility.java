package com.ui.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> readExcelFile(String fileName) throws InvalidFormatException, IOException {
        File xlsxFile = new File(System.getProperty("user.dir") + "/testData/" + fileName + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(xlsxFile));
        List<User> userList = new ArrayList<>();
        XSSFSheet sheet = workbook.getSheet("loginData.xlsx");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            User user = new User(username, password);
            userList.add(user);
        }
        return userList.iterator();
    }
}
