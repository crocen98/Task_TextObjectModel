package com.epam.tom.service.comparator;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class WordLengthComparatorTest {
private final WordLengthComparator comparator = new WordLengthComparator();


@Test
    public void shouldSortArrayOfStringsByLength(){
    TextComponent[] words = {
            new Word("12") ,
            new Word("12511") ,
            new Word("3541"),
            new Word("23451124355"),
            new Word("1"),
            new Word("34")};

    TextComponent[] sortedWords = {
            new Word("1"),
            new Word("12"),
            new Word("34"),
            new Word("3541"),
            new Word("12511") ,
            new Word("23451124355"),
    };

    Arrays.sort(words,comparator);

    Assert.assertEquals(sortedWords.length ,words.length);
    Assert.assertTrue(Arrays.equals(sortedWords ,words));
}

}
