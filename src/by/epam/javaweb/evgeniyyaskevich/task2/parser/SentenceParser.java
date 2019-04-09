package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;

import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final SentenceParser INSTANCE = new SentenceParser();
    private static final String REGEX_FOR_SPLITTING_INTO_SENTENCES = "((?<=([?!.]))(?=[^.])\\s)|(?<=(\\.{3})\\s)";

    private SentenceParser() {}

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Component parse(Component paragraphComponent, String paragraph) {
        Pattern pattern = Pattern.compile(getRegexForParse());
        String[] parts = pattern.splitAsStream(paragraph)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        for (String part : parts) {
            if (nextParser == null) {
                paragraphComponent.add(new Leaf(part, RecordType.SENTENCE));
            } else {
                Component sentenceComponent = new Composite(RecordType.SENTENCE);
                paragraphComponent.add(sentenceComponent);
                nextParser.parse(sentenceComponent, part);
            }
        }
        return paragraphComponent;
    }

    @Override
    public String getRegexForParse() {
        return REGEX_FOR_SPLITTING_INTO_SENTENCES;
    }
}
