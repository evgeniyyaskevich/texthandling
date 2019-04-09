package by.epam.javaweb.evgeniyyaskevich.task2.interpreter.expression;

import by.epam.javaweb.evgeniyyaskevich.task2.interpreter.Context;

public class NumberExpression extends AbstractExpression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
