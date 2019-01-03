package com.epam.tom.entity;

public enum NodeType {
    TEXT(0),
    PARAGRAPH(1),
    OFFER(2),
    LEXEME(3),
    WORD(4),
    SYMBOL(4);

    NodeType(int level){
        this.levelOfNesting = level;
    }

    private int levelOfNesting;
    public int getLevelOfNesting(){
        return this.levelOfNesting;
    }
}
