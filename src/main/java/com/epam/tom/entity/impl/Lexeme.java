package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lexeme extends TextPart {
    private List<TextComponent> words = new ArrayList<>();

    public Lexeme(List<TextComponent> words){
        super(NodeType.LEXEME);
        this.words = words;
    }

    public Lexeme(){
        super(NodeType.LEXEME);
    }

    @Override
    public String getContent() {
        final StringBuilder builder = new StringBuilder();
        words.stream().
                forEachOrdered((lexeme)->{
                    builder.append(lexeme.getContent());
                });
        builder.append(' ');
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

    @Override
    public void append(TextComponent part) {
        words.add(part);
    }

    @Override
    public void remove(TextComponent part) {
        words.remove(part);
    }

    @Override
    public List<TextComponent> getTextComponent() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lexeme lexeme = (Lexeme) o;
        return Objects.equals(words, lexeme.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), words);
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "words=" + words +
                '}';
    }
}
