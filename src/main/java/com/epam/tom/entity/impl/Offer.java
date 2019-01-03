package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Offer extends TextPart {
    private List<TextComponent> lexemes = new ArrayList<TextComponent>();

    public Offer(List<TextComponent> lexemes){
        super(NodeType.OFFER);
        this.lexemes = lexemes;
    }

    public Offer(){
        super(NodeType.OFFER);
    }

    @Override
    public String getContent() {
        final StringBuilder builder = new StringBuilder();
        lexemes.stream()
                .forEachOrdered((lexeme)->{
                    builder.append(lexeme.getContent());
                    builder.append(' ');
                });
        builder.delete(builder.length()-1,builder.length());
        return builder.toString();
    }

    @Override
    public void append(TextComponent part) {
        lexemes.add(part);
    }

    @Override
    public void remove(TextComponent part) {
        lexemes.remove(part);
    }

    @Override
    public List<TextComponent> getTextComponent() {
        return lexemes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(lexemes, offer.lexemes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lexemes);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "lexemes=" + lexemes +
                '}';
    }
}
