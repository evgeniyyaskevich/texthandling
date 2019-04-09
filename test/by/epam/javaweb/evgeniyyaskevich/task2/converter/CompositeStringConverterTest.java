package by.epam.javaweb.evgeniyyaskevich.task2.converter;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType.*;
import static by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType.PUNCTUATION_MARK;
import static by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType.WORD;

public class CompositeStringConverterTest {

    private CompositeStringConverter compositeStringConverter = CompositeStringConverter.getInstance();

    private Component formCompositeForConverting() {
        Component result = new Composite(TEXT);

        Composite paragraphOne = new Composite(PARAGRAPH);
        Composite paragraphTwo = new Composite(PARAGRAPH);

        Composite sentenceOne = new Composite(SENTENCE);
        Composite sentenceTwo = new Composite(SENTENCE);
        Composite sentenceThree = new Composite(SENTENCE);

        //first sentence
        Composite lexeme = new Composite(LEXEME);
        lexeme.add(new Leaf("Hello", WORD));
        lexeme.add(new Leaf(",", PUNCTUATION_MARK));
        sentenceOne.add(lexeme);

        lexeme = new Composite(LEXEME);
        lexeme.add(new Leaf("my", WORD));
        sentenceOne.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("dear", WORD));
        sentenceOne.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("friend", WORD));
        lexeme.add(new Leaf("!", PUNCTUATION_MARK));
        sentenceOne.add(lexeme);

        //second sentence
        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("About", WORD));
        sentenceTwo.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("0", WORD));
        sentenceTwo.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("people", WORD));
        sentenceTwo.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("here", WORD));
        lexeme.add(new Leaf(".", PUNCTUATION_MARK));
        sentenceTwo.add(lexeme);

        //third sentence
        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("I", WORD));
        sentenceThree.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("am", WORD));
        sentenceThree.add(lexeme);

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("bad", WORD));
        lexeme.add(new Leaf(".", PUNCTUATION_MARK));
        sentenceThree.add(lexeme);

        paragraphOne.add(sentenceOne);
        paragraphTwo.add(sentenceTwo);
        paragraphTwo.add(sentenceThree);
        result.add(paragraphOne);
        result.add(paragraphTwo);

        return result;
    }

    @Test
    public void convertToString() {

        String expected = "\n\tHello, my dear friend! \n\tAbout 0 people here. " +
                "I am bad. ";
        Component text = formCompositeForConverting();

        String result = compositeStringConverter.convertToString(text);

        Assert.assertEquals(result, expected);
    }
}