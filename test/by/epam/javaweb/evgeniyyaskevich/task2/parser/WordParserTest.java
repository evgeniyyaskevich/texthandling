package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;


public class WordParserTest {

    private WordParser wordParser = WordParser.getInstance();

    @Test
    public void parseTest() {
        String lexeme = "Hello.";
        Component expected = new Composite(RecordType.LEXEME);
        expected.add(new Leaf("Hello", RecordType.WORD));
        expected.add(new Leaf(".", RecordType.PUNCTUATION_MARK));

        Component result = new Composite(RecordType.LEXEME);
        wordParser.parse(result, lexeme);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }

    @Test
    public void parseTestTwo() {
        String sentence = "Hello world.";
        Component expected = new Composite(RecordType.SENTENCE);
        expected.add(new Leaf("Hello", RecordType.WORD));
        expected.add(new Leaf("world", RecordType.WORD));
        expected.add(new Leaf(".", RecordType.PUNCTUATION_MARK));

        Component result = new Composite(RecordType.SENTENCE);
        wordParser.parse(result, sentence);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }
}