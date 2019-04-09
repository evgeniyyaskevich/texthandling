package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;

public abstract class AbstractParser {
    protected AbstractParser nextParser;

    public abstract Component parse(Component component, String data);

    public abstract String getRegexForParse();

    public void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }
}
