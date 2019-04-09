package by.epam.javaweb.evgeniyyaskevich.task2.converter;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import by.epam.javaweb.evgeniyyaskevich.task2.exception.IllegalRecordTypeExpression;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CompositeStringConverter {

    private static final Logger LOGGER = LogManager.getLogger(CompositeStringConverter.class);
    private static CompositeStringConverter INSTANCE = new CompositeStringConverter();

    private CompositeStringConverter() {}

    public static CompositeStringConverter getInstance() {
        return INSTANCE;
    }

    public String convertToString(Component component) {
        StringBuilder builder = new StringBuilder();
        String mark = getCorrespondingMark(component);

        if (component instanceof Leaf) {
            builder.append(component);
            builder.append(mark);
        } else if (component instanceof Composite) {
            if (isParagraph(component)) {
                builder.append(mark);
            }

            Composite composite = (Composite) component;
            for (Component child : composite.getListOfChildren()) {
                builder.append(convertToString(child));
            }

            if (!isParagraph(component)) {
                builder.append(mark);
            }
        }
        return builder.toString();
    }

    private String getCorrespondingMark(Component component) {
        RecordType recordType = component.getRecordType();
        String punctuationMark = "";
        switch (recordType) {
            case LEXEME:
                punctuationMark = " ";
                break;
            case PARAGRAPH:
                punctuationMark = "\n\t";
                break;
            case TEXT:
            case PUNCTUATION_MARK:
            case SYMBOL:
            case WORD:
            case SENTENCE:
                break;
            default:
                LOGGER.warn("IllegalRecordTypeException.");
                throw new IllegalRecordTypeExpression("There are no such type in switch!");
        }
        return punctuationMark;
    }

    private boolean isParagraph(Component component) {
        return component.getRecordType() == RecordType.PARAGRAPH;
    }
}
