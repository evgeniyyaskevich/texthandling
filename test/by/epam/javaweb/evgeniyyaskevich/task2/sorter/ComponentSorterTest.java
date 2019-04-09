package by.epam.javaweb.evgeniyyaskevich.task2.sorter;

import by.epam.javaweb.evgeniyyaskevich.task2.converter.CompositeStringConverter;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Composite;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.Leaf;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType.*;

public class ComponentSorterTest {

    private ComponentSorter componentSorter = ComponentSorter.getInstance();

    private Component formTextComposite() {
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

        lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("Hello", RecordType.WORD));
        lexeme.add(new Leaf(".", RecordType.PUNCTUATION_MARK));
        sentenceThree.add(lexeme);

        paragraphOne.add(sentenceOne);
        paragraphOne.add(sentenceTwo);
        paragraphTwo.add(sentenceThree);
        result.add(paragraphOne);
        result.add(paragraphTwo);

        return result;
    }

    /**
     * Test depends from convertToString() method.
     */
    @Test
    public void sortWordsInTextSentencesByLength() {
        CompositeStringConverter compositeStringConverter = CompositeStringConverter.getInstance();
        Component text = formTextComposite();
        String expectedText = "\n\tmy, dear Hello friend! 0 here About people. \n\tHello. ";

        componentSorter.sortWordsInSentencesByLength(text);
        String result = compositeStringConverter.convertToString(text);

        Assert.assertEquals(result, expectedText);
    }

    @Test
    public void sortParagraphInTextBySentenceAmount() {
        Component text = formTextComposite();
        List<Component> expected = text.getListOfChildren();
        Collections.reverse(expected);

        componentSorter.sortParagraphInTextBySentenceAmount(text);

        Assert.assertEquals(text.getListOfChildren(), expected);
    }

    @Test
    public void sortLexemeInSentenceByCharacterFrequency() {
        Component sentence = new Composite(RecordType.SENTENCE);
        Component sentenceResult = new Composite(RecordType.SENTENCE);

        Component lexeme = new Composite(RecordType.LEXEME);
        lexeme.add(new Leaf("aabbbcccc", RecordType.WORD));
        lexeme.add(new Leaf(",", RecordType.PUNCTUATION_MARK));
        sentence.add(lexeme);

        Component lexemeTwo = new Composite(RecordType.LEXEME);
        lexemeTwo.add(new Leaf("abbccc", RecordType.WORD));
        lexemeTwo.add(new Leaf(".", RecordType.PUNCTUATION_MARK));
        sentence.add(lexemeTwo);

        sentenceResult.add(lexemeTwo);
        sentenceResult.add(lexeme);


        componentSorter.sortLexemeInSentenceByCharacterFrequency(sentence,'b');

        Assert.assertEquals(sentence, sentenceResult);
    }
}