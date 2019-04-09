package by.epam.javaweb.evgeniyyaskevich.task2.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {
    private Deque<Integer> contextValues = new ArrayDeque<>();

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        this.contextValues.push(value);
    }
}

