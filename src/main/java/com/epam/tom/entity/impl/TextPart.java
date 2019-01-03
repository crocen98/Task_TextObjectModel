package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.List;
import java.util.Objects;

public abstract class TextPart implements TextComponent {

    private final NodeType type;

    public TextPart(NodeType type) {
        this.type = type;
    }


    public abstract void append(TextComponent part);
    public abstract void remove(TextComponent part);
    public abstract   List<TextComponent> getTextComponent();

    @Override
    public NodeType getNodeType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextPart part = (TextPart) o;
        return type == part.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "TextPart{" +
                "type=" + type +
                '}';
    }
}
