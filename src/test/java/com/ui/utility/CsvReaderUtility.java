package com.ui.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvReaderUtility {
    public static Iterator<User> readCsvFile(String fileName) {
        File csvFile = new File(System.getProperty("user.dir") + "/testData/" + fileName + ".csv");
        FileReader fileReader;
        CSVReader csvReader;
        String[] data;
        User user;
        List<User> userList;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext(); // Skip the header line\
            userList = new ArrayList<>();
            while ((data = csvReader.readNext()) != null) {
                user = new User(data[0], data[1]);
                userList.add(user);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
            return userList.iterator();
    }
}
