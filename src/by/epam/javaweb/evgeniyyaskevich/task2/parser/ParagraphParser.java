package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;

import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {

    private static final ParagraphParser INSTANCE = new ParagraphParser();
    private static final String REGEX_FOR_SPLITTING_INTO_PARAGRAPH = "((\\t| {4}))";

    private ParagraphParser() {}

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Component parse(Component textComponent, String text) {
        Pattern pattern = Pattern.compile(getRegexForParse());
        String[] parts = pattern.splitAsStream(text)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        for (String part : parts) {
            if (nextParser == null) {
                textComponent.add(new Leaf(part, RecordType.PARAGRAPH));
            } else {
                Component paragraphComponent = new Composite(RecordType.PARAGRAPH);
                textComponent.add(paragraphComponent);
                nextParser.parse(paragraphComponent, part);
            }
        }
        return textComponent;
    }

    @Override
    public String getRegexForParse() {
        return REGEX_FOR_SPLITTING_INTO_PARAGRAPH;
    }
}
