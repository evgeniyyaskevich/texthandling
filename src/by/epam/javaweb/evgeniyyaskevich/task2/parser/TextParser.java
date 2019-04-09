package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;

public class TextParser extends AbstractParser {

    private static final TextParser INSTANCE = new TextParser();

    private TextParser() {}

    public static TextParser getInstance() {
        return INSTANCE;
    }

    public Component parse(Component textComponent, String text) {
        nextParser.parse(textComponent, text);
        return textComponent;
    }

    @Override
    public String getRegexForParse() {
        throw new UnsupportedOperationException("There are no Regex For Parse in text.");
    }
}
