package com.ui.dataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.ui.utility.CsvReaderUtility;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {
    @DataProvider(name = "loginDataProvider")
    public Iterator<Object[]> loginDataProvider() {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "/testData/loginData.json");
        FileReader fileReader;
        try {
            fileReader = new FileReader(testDataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        TestData testData = gson.fromJson(fileReader, TestData.class);
        List<Object[]> dataToReturn = new ArrayList<Object[]>();
        for (User user : testData.getData()) {
            dataToReturn.add(new Object[]{user});
        }
        return dataToReturn.iterator();
    }

    @DataProvider(name = "loginDataProviderFromCsv")
    public Iterator<Object[]> loginDataProviderFromCsv() {
        List<Object[]> dataToReturn = new ArrayList<>();
        Iterator<User> userIterator = CsvReaderUtility.readCsvFile("loginData");
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            dataToReturn.add(new Object[]{user});
        }
        return dataToReturn.iterator();
    }

    @DataProvider(name = "loginDataProviderFromExcel")
    public Iterator<Object[]> loginDataProviderFromExcel() throws IOException, InvalidFormatException {
        List<Object[]> dataToReturn = new ArrayList<>();
        Iterator<User> userIterator = com.ui.utility.ExcelReaderUtility.readExcelFile("loginData");
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            dataToReturn.add(new Object[]{user});
        }
        return dataToReturn.iterator();
    }
}
