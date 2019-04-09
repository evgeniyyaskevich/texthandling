package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;

import java.util.regex.Pattern;

//In future: need to check level of nextParser. If nextParser has higher level, than current - thrown Exception.
public class WordParser extends AbstractParser {

    private static final WordParser INSTANCE = new WordParser();
    private static final String REGEX_FOR_PARSE_BY_PUNCTUATION_MARK = "(?=[,!?().])|(?<=\\()|\\s";
    private static final String REGEX_FOR_PUNCTUATION_MARK = "^([,!?]|(\\.{1,3}))$";

    private WordParser() {}

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Component parse(Component lexemeComponent, String lexeme) {
        Pattern pattern = Pattern.compile(getRegexForParse());
        String[] parts = pattern.split(lexeme);

        for (String part : parts) {

            if (nextParser == null) {
                if (isPunctuationMark(part)) {
                    lexemeComponent.add(new Leaf(part, RecordType.PUNCTUATION_MARK));
                } else {
                    lexemeComponent.add(new Leaf(part, RecordType.WORD));
                }
            } else {
                if (isPunctuationMark(part)) {
                    lexemeComponent.add(new Leaf(part, RecordType.PUNCTUATION_MARK));
                } else {
                    Component wordComponent = new Composite(RecordType.WORD);
                    lexemeComponent.add(wordComponent);
                    nextParser.parse(wordComponent, part);
                }
            }
        }
        return lexemeComponent;
    }

    @Override
    public String getRegexForParse() {
        return REGEX_FOR_PARSE_BY_PUNCTUATION_MARK;
    }

    private boolean isPunctuationMark(String str) {
        return str.matches(REGEX_FOR_PUNCTUATION_MARK);
    }
}
