package de.sixbits.squeakyjava.helper;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileTestHelpers {

    public static String loadJson(String fileName) throws IOException {
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
