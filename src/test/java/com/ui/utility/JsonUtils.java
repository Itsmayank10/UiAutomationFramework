package com.ui.utility;

import com.google.gson.Gson;
import com.ui.constants.ENV;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtils {
    public static String readJson(ENV env) {
        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "/" + "config/config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Config config = gson.fromJson(fileReader, Config.class);
        Environment environment = config.getEnvironments().get("QA");
        return environment.getUrl();
    }
}
