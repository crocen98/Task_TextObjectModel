package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text extends TextPart{

   private List<TextComponent> paragrapghs = new ArrayList<>();

    public Text(List<TextComponent> paragraphs){
        super(NodeType.TEXT);
        this.paragrapghs = paragraphs;
    }

    public Text(){
        super(NodeType.TEXT);
    }

    @Override
    public String getContent() {
        final StringBuilder builder = new StringBuilder();
        paragrapghs.stream().
                forEachOrdered((paragraph)->{
                    builder.append(paragraph.getContent());
                    builder.append('\n');
                });
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

    @Override
    public void append(TextComponent part) {
        paragrapghs.add(part);
    }

    @Override
    public void remove(TextComponent part) {
        paragrapghs.remove(part);
    }

    @Override
    public List<TextComponent> getTextComponent() {
        return paragrapghs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Text text = (Text) o;
        return Objects.equals(paragrapghs, text.paragrapghs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paragrapghs);
    }

    @Override
    public String toString() {
        return "Text{" +
                "paragrapghs=" + paragrapghs +
                '}';
    }
}
