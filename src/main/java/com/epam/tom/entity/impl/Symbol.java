package com.epam.tom.entity.impl;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;

import java.util.Objects;

public class Symbol implements TextComponent {
    private final NodeType type = NodeType.SYMBOL;
    private String mark;

    public Symbol(String mark){
        this.mark =mark;
    }

    public String getContent() {
        return mark;
    }

    @Override
    public NodeType getNodeType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return type == symbol.type &&
                Objects.equals(mark, symbol.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mark);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "type=" + type +
                ", mark='" + mark + '\'' +
                '}';
    }
}
