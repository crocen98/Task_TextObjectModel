package com.epam.tom.service.interpret;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {
    private Deque<Integer> deque = new ArrayDeque<>();
    public Integer pop(){
        return deque.pop();
    }

    public void push(Integer value){
        deque.push(value);
    }
}
