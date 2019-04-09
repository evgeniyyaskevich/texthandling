package by.epam.javaweb.evgeniyyaskevich.task2.interpreter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InterpreterTest {

    @DataProvider(name = "interpretData")
    public Object[][] interpretData() {
        return new Object[][] {
                {"15 20 3 & |", 15|20&3},
                {"(71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", (71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78},
                {"5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1)", 5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1)},
                {"6&9|(3&4)", 6&9|(3&4)},
                {"3>>5", 3>>5},
                {"13<<2", 13<<2},
                {" (8^5|1&2<<(2|5>>2&71))|1200",  (8^5|1&2<<(2|5>>2&71))|1200},
                {"125^12&3|125>>12", 125^12&3|125>>12}
        };
    }

    @Test(dataProvider = "interpretData")
    public void interpret(String expression, int expected) {
        Interpreter interpreter = Interpreter.getInstance();

        int result = interpreter.interpret(expression);

        Assert.assertEquals(result, expected);
    }
}