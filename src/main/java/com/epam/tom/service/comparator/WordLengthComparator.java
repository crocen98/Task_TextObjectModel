package com.epam.tom.service.comparator;

import com.epam.tom.entity.TextComponent;

import java.util.Comparator;

public class WordLengthComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent first, TextComponent second) {
        String firstContent = first.getContent();
        String secondContent = second.getContent();

        return Integer.compare(firstContent.length(),secondContent.length());
    }
}
