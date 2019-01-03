package com.epam.tom.service;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.TextPart;
import com.epam.tom.exception.NotSupportedSortException;

import java.util.Comparator;

public interface TOMSorter {
    void sort( TextPart component,Comparator<TextComponent> comparator) throws NotSupportedSortException;
}
