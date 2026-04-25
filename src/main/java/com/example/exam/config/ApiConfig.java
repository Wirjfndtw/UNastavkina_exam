package com.example.exam.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ApiConfig {
    public static final String BASE_URL;
    public static final String API_KEY;

    static {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (Exception e) {
            System.err.println("Не удалось загрузить application.properties");
        }
        BASE_URL = props.getProperty("api.base-url", "http://10.82.196.214:8085");
        API_KEY = props.getProperty("api.key", "bookstore-2026-secret");
    }
}
