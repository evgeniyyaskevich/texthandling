package by.epam.javaweb.evgeniyyaskevich.task2.interpreter.expression;

import by.epam.javaweb.evgeniyyaskevich.task2.interpreter.Context;

public class RightBitShiftExpression extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        Integer rightArgument = context.popValue();
        Integer leftArgument = context.popValue();
        context.pushValue(leftArgument >> rightArgument);
    }
}
