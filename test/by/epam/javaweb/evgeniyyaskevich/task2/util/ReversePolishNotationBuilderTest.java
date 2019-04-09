package by.epam.javaweb.evgeniyyaskevich.task2.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReversePolishNotationBuilderTest {

    @DataProvider(name = "reversePolishNotationBuilderData")
    public Object[][] reversePolishNotationBuilderData() {
        return new Object[][] {
                {"13>>2", "13 2 >>"},
                {"5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1) ", "5 1 2 & 3 4 2 5 ^ 6 47 & | & 3 | | 2 | & 1 | |"},
                {"(8^5|1&2<<(2|5>>2&71))|1200", "8 5 ^ 1 2 2 5 2 >> 71 & | << & | 1200 |"},
                {"15|20&3", "15 20 3 & |"}
        };
    }

    @Test(dataProvider = "reversePolishNotationBuilderData")
    public void reversePolishNotationBuilder(String expression, String expected) {
        ReversePolishNotationBuilder reversePolishNotationBuilder = ReversePolishNotationBuilder.getInstance();

        reversePolishNotationBuilder.parse(expression);
        String result = reversePolishNotationBuilder.formResult();

        Assert.assertEquals(result, expected);
    }
}