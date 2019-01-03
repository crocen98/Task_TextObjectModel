package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.Objects;

public class Word implements TextComponent {
    private final NodeType type = NodeType.WORD;
    private String word;

    public Word( String word){
        this.word =word;
    }

    public String getContent() {
        return word;
    }

    @Override
    public NodeType getNodeType() {
        return type;
    }

    @Override
    public String toString() {
        return "Word{" +
                "type=" + type +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return type == word1.type &&
                Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, word);
    }
}
