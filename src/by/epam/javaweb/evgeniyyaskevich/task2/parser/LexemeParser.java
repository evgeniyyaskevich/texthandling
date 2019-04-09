package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import by.epam.javaweb.evgeniyyaskevich.task2.interpreter.Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {

    private static final LexemeParser INSTANCE = new LexemeParser();
    private static final String REGEX_FOR_LEXEME = "\\s+";
    private static final String REGEX_FOR_BIT_OPERATION = "[&^|]|(<<)|(>>)";

    private LexemeParser() {}

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Component parse(Component sentenceComponent, String sentence) {
        Pattern pattern = Pattern.compile(getRegexForParse());
        String[] parts = pattern.splitAsStream(sentence)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        Pattern bitPattern = Pattern.compile(REGEX_FOR_BIT_OPERATION);
        for (String part : parts) {
            Matcher bitMatcher = bitPattern.matcher(part);
            if (bitMatcher.find()) {
                part = Integer.toString(interpretBitExpression(part));
            }

            if (nextParser == null) {
                sentenceComponent.add(new Leaf(part, RecordType.LEXEME));
            } else {
                Component lexemeComponent = new Composite(RecordType.LEXEME);
                sentenceComponent.add(lexemeComponent);
                nextParser.parse(lexemeComponent, part);
            }
        }
        return sentenceComponent;
    }

    @Override
    public String getRegexForParse() {
        return REGEX_FOR_LEXEME;
    }

    private int interpretBitExpression(String expression) {
        Interpreter interpreter = Interpreter.getInstance();
        return interpreter.interpret(expression);
    }
}