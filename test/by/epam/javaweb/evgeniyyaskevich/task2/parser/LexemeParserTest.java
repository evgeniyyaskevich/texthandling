package by.epam.javaweb.evgeniyyaskevich.task2.parser;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LexemeParserTest {

    private LexemeParser lexemeParser = LexemeParser.getInstance();

    @Test
    public void parseTest() {
        String sentence = "Hello world, how are you?";
        Component expected = new Composite(RecordType.SENTENCE);
        expected.add(new Leaf("Hello", RecordType.LEXEME));
        expected.add(new Leaf("world,", RecordType.LEXEME));
        expected.add(new Leaf("how", RecordType.LEXEME));
        expected.add(new Leaf("are", RecordType.LEXEME));
        expected.add(new Leaf("you?", RecordType.LEXEME));

        Component result = new Composite(RecordType.SENTENCE);
        lexemeParser.parse(result, sentence);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }

    @Test
    public void parseTestTwo() {
        String sentence = "Happy new (8^5|1&2<<(2|5>>2&71))|1200 year!";
        Component expected = new Composite(RecordType.SENTENCE);
        expected.add(new Leaf("Happy", RecordType.LEXEME));
        expected.add(new Leaf("new", RecordType.LEXEME));
        expected.add(new Leaf("1213", RecordType.LEXEME));
        expected.add(new Leaf("year!", RecordType.LEXEME));

        Component result = new Composite(RecordType.SENTENCE);
        lexemeParser.parse(result, sentence);

        Assert.assertEquals(result.getListOfChildren(), expected.getListOfChildren());
    }



}