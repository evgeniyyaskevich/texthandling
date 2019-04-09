package by.epam.javaweb.evgeniyyaskevich.task2.interpreter;

import by.epam.javaweb.evgeniyyaskevich.task2.exception.IllegalExpressionException;
import by.epam.javaweb.evgeniyyaskevich.task2.interpreter.expression.*;
import by.epam.javaweb.evgeniyyaskevich.task2.util.ReversePolishNotationBuilder;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private static final Interpreter INSTANCE = new Interpreter();

    private List<AbstractExpression> expressionList = new ArrayList<>();
    private String reversePolishNotation;

    private Interpreter() {}

    public static Interpreter getInstance() {
        return INSTANCE;
    }

    public int interpret(String expression) {
        init(expression);
        return calculate();
    }

    private int calculate() throws IllegalExpressionException {
        Context context = new Context();
        for (AbstractExpression expr : expressionList) {
            expr.interpret(context);
        }
        return context.popValue();
    }

    private void init(String expression) {
        ReversePolishNotationBuilder reversePolishNotationBuilder = ReversePolishNotationBuilder.getInstance();
        reversePolishNotationBuilder.parse(expression);
        this.reversePolishNotation = reversePolishNotationBuilder.formResult();
        expressionListInitParse();
    }

    private void expressionListInitParse() throws IllegalExpressionException {
        for (String expression : reversePolishNotation.split("\\s")) {
            switch(expression) {
                case "^":
                    expressionList.add(new ExlusiveOrExpression());
                    break;
                case "&":
                    expressionList.add(new BitAndExpression());
                    break;
                case ">>":
                    expressionList.add(new RightBitShiftExpression());
                    break;
                case "<<":
                    expressionList.add(new LeftBitShiftExpression());
                    break;
                case "|":
                    expressionList.add(new BitOrExpression());
                    break;
                default:
                    if (expression.matches("^\\d+$")) {
                        expressionList.add(new NumberExpression(Integer.parseInt(expression)));
                        break;
                    } else {
                        throw new IllegalExpressionException("Undefined expression: " + expression);
                    }
            }
        }
    }

}
