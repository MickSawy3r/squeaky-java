package de.sixbits.squeakyjava.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileTestHelpers {

    public static String loadJson(String fileName) throws IOException {
        if (fileName.toCharArray()[0] != '/') {
            fileName = "/" + fileName;
        }

        URL url = FileTestHelpers.class.getResource(fileName);
        if (url == null) {
            throw new FileNotFoundException();
        }
        File file = new File(url.getFile());

        StringBuilder jsonString = new StringBuilder();

        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stream.forEach(jsonString::append);
        }

        return jsonString.toString();
    }
}
