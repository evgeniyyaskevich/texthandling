package by.epam.javaweb.evgeniyyaskevich.task2.sorter;

import by.epam.javaweb.evgeniyyaskevich.task2.entity.Component;
import by.epam.javaweb.evgeniyyaskevich.task2.entity.RecordType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComponentSorter {

    private static class SingletonHolder {
        private final static ComponentSorter INSTANCE = new ComponentSorter();
    }

    private ComponentSorter() {}

    public static ComponentSorter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void sortWordsInSentencesByLength(Component textComponent) {
        List<Component> paragraphs = textComponent.getListOfChildren();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = paragraph.getListOfChildren();
            for (Component sentence : sentences) {
                List<Component> lexemes = sentence.getListOfChildren();
                List<Component> words = new ArrayList<>();
                for (Component lexeme : lexemes) {
                    words.add(takeWordFromLexeme(lexeme));
                }
                words.sort(this::compareWordsByLength);
                for (Component lexeme : lexemes) {
                    lexeme.getListOfChildren().add(0, words.remove(0));
                }
            }
        }
    }

    public void sortParagraphInTextBySentenceAmount(Component textComponent) {
        List<Component> paragraphs = textComponent.getListOfChildren();
        paragraphs.sort(this::compareParagraphBySentence);
    }

    public void sortLexemeInSentenceByCharacterFrequency(Component sentenceComponent, Character character) {
        List<Component> lexemes = sentenceComponent.getListOfChildren();
        Comparator<Component> lexemeComparator = (o1, o2) -> compareLexemesByCharacterFrequency(o1, o2, character);
        lexemeComparator.thenComparing(this::convertLexemeToString);
        lexemes.sort(lexemeComparator);
    }

    private int compareLexemesByCharacterFrequency(Component lexeme, Component lexemeTwo, Character character) {
        String lexemeString = convertLexemeToString(lexeme);
        String lexemeStringTwo = convertLexemeToString(lexemeTwo);
        int charCount = lexemeString.split(character.toString()).length;
        int charCountTwo = lexemeStringTwo.split(character.toString()).length;
        return Integer.compare(charCount, charCountTwo);
    }

    private int compareWordsByLength(Component word, Component wordTwo) {
        int length = word.toString().length();
        int lengthTwo = wordTwo.toString().length();
        return Integer.compare(length, lengthTwo);
    }

    private int compareParagraphBySentence(Component paragraphComponent, Component paragraphComponentTwo) {
        int size = paragraphComponent.getListOfChildren().size();
        int sizeTwo = paragraphComponentTwo.getListOfChildren().size();
        return Integer.compare(size, sizeTwo);
    }

    private Component takeWordFromLexeme(Component lexeme) {
        for (Component component : lexeme.getListOfChildren()) {
            if (component.getRecordType() == RecordType.WORD) {
                lexeme.remove(component);
                return component;
            }
        }
        return null;
    }

    private String convertLexemeToString(Component lexemeComponent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : lexemeComponent.getListOfChildren()) {
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }
}
