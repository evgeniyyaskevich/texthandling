package by.epam.javaweb.evgeniyyaskevich.task2.util;

import by.epam.javaweb.evgeniyyaskevich.task2.exception.IllegalExpressionException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Note: Singleton doesn`t need here. It is inconvenient.
public class ReversePolishNotationBuilder {
    private static final String REGEX_FOR_NUMBER = "^\\d+$";
    private static final String REGEX_FOR_BIT_OPERATIONS = "^[&^|]|(>>)|(<<)$";
    private static final String REGEX_FOR_SPLIT_BIT_EXPRESSION = "[&^|()]|(<<)|(>>)|([0-9]+)";

    private Stack<String> reversePolishNotation = new Stack<>();
    private Stack<String> operationStack = new Stack<>();

    private static class SingletonHolder {
        private final static ReversePolishNotationBuilder INSTANCE = new ReversePolishNotationBuilder();
    }

    private ReversePolishNotationBuilder() {}

    public static ReversePolishNotationBuilder getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void parse(String expression) throws IllegalExpressionException {
        clear();

        Pattern pattern = Pattern.compile(REGEX_FOR_SPLIT_BIT_EXPRESSION);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String currentExpression = matcher.group();
            if (currentExpression.equals("(")) {
                operationStack.push(currentExpression);
            } else if (isNumber(currentExpression)) {
                reversePolishNotation.push(currentExpression);
            } else if (isBitOperation(currentExpression)) {
                while (!operationStack.empty()
                        && getPriorityOfExpression(operationStack.peek()) >= getPriorityOfExpression(currentExpression)) {
                    reversePolishNotation.push(operationStack.pop());
                }
                operationStack.push(currentExpression);
            } else if (currentExpression.equals(")")) {
                while (!operationStack.empty() && !operationStack.peek().equals("(")) {
                    reversePolishNotation.push(operationStack.pop());
                }
                operationStack.pop();
            }
        }
        while (!operationStack.empty()) {
            reversePolishNotation.push(operationStack.pop());
        }
    }

    public String formResult() {
        //TODO: Note: use join here from Java 8
        StringBuilder result = new StringBuilder();
        for (String element : reversePolishNotation) {
            result.append(element);
            result.append(' ');
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private void clear() {
        reversePolishNotation.clear();
        operationStack.clear();
    }


    private boolean isNumber(String expression) {
        return expression.matches(REGEX_FOR_NUMBER);
    }

    private boolean isBitOperation(String expression) {
        return expression.matches(REGEX_FOR_BIT_OPERATIONS);
    }

    private int getPriorityOfExpression(String expression) throws IllegalExpressionException {
        int priority;

        switch (expression) {
            case "<<":
            case ">>":
                priority = 4;
                break;
            case "&":
                priority = 3;
                break;
            case "^":
                priority = 2;
                break;
            case "|":
                priority = 1;
                break;
            case "(":
            case ")":
                priority = 0;
                break;
            default:
                throw new IllegalExpressionException("Undefined expression: " + expression);
        }
        return priority;
    }

}
