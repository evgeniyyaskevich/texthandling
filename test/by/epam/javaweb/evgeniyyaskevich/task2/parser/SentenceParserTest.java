package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SentenceParserTest {

    private SentenceParser sentenceParser = SentenceParser.getInstance();

    @Test
    public void parseTest() {
        String lexeme = "Hello. I am Mike. How are you?";
        Component expected = new Composite(RecordType.PARAGRAPH);
        expected.add(new Leaf("Hello.", RecordType.SENTENCE));
        expected.add(new Leaf("I am Mike.", RecordType.SENTENCE));
        expected.add(new Leaf("How are you?", RecordType.SENTENCE));

        Component result = new Composite(RecordType.SENTENCE);
        sentenceParser.parse(result, lexeme);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }

}