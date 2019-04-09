package by.epam.javaweb.evgeniyyaskevich.task2.interpreter.expression;

import by.epam.javaweb.evgeniyyaskevich.task2.interpreter.Context;

import java.util.AbstractSet;

public class BitOrExpression extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() | context.popValue());
    }
}
