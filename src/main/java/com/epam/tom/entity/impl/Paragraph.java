package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph extends TextPart {
    private List<TextComponent> offers = new ArrayList<>();

    public Paragraph(List<TextComponent> offers){
        super(NodeType.PARAGRAPH);
        this.offers = offers;
    }

    public Paragraph(){
        super(NodeType.PARAGRAPH);
    }

    @Override
    public String getContent() {
        final StringBuilder builder = new StringBuilder();
        builder.append('\t');
        offers.stream().
                forEachOrdered((offer)->{
                    builder.append(offer.getContent());
                    builder.append(' ');
                });
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

    @Override
    public void append(TextComponent part) {
        offers.add(part);
    }

    @Override
    public void remove(TextComponent part) {
        offers.remove(part);
    }

    @Override
    public List<TextComponent> getTextComponent() {
        return offers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(offers, paragraph.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), offers);
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "offers=" + offers +
                '}';
    }
}
