package de.sixbits.squeakyjava.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileTestHelpers {

    public static String loadJson(String fileName) {
        if (fileName.toCharArray()[0] != '/') {
            fileName = "/" + fileName;
        }

        InputStream inputStream = FileTestHelpers.class.getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream, StandardCharsets.UTF_8
        );

        StringBuilder jsonString = new StringBuilder();
        new BufferedReader(inputStreamReader).lines().forEach(jsonString::append);
        return jsonString.toString();
    }
}
