package com.epam.tom.reader.impl;

import com.epam.tom.reader.TextReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader implements TextReader
{
    Path filePath;

    public FileReader(String filePath){
        this.filePath = Paths.get(filePath);
    }

    private List<String> readAllLines() throws IOException {
        return Files.readAllLines(filePath, Charset.defaultCharset());
    }

    @Override
    public String readText() throws IOException {
        StringBuilder textBuilder;
        textBuilder = new StringBuilder();
        List<String> textLines = readAllLines();

        textLines.forEach(
                textLine -> {
                    textBuilder.append(textLine);
                    textBuilder.append('\n');} );
        textBuilder.deleteCharAt(textBuilder.length() - 1);
        return textBuilder.toString();
    }
}
