package by.epam.javaweb.evgeniyyaskevich.task2.exception;

public class IllegalRecordTypeExpression extends IllegalArgumentException {
    public IllegalRecordTypeExpression() {
        super();
    }

    public IllegalRecordTypeExpression(String s) {
        super(s);
    }

    public IllegalRecordTypeExpression(String message, Throwable cause) {
        super(message, cause);
    }
}
