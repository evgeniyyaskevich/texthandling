package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParagraphParserTest {

    private ParagraphParser paragraphParser = ParagraphParser.getInstance();

    @Test
    public void parseTest() {
        String lexeme = "\tHello.    ParagraphTwo.    What about the third?";
        Component expected = new Composite(RecordType.TEXT);
        expected.add(new Leaf("Hello.", RecordType.PARAGRAPH));
        expected.add(new Leaf("ParagraphTwo.", RecordType.PARAGRAPH));
        expected.add(new Leaf("What about the third?", RecordType.PARAGRAPH));

        Component result = new Composite(RecordType.TEXT);
        paragraphParser.parse(result, lexeme);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }

}