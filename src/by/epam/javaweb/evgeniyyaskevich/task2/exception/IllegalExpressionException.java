package by.epam.javaweb.evgeniyyaskevich.task2.exception;

public class IllegalExpressionException extends IllegalArgumentException {
    public IllegalExpressionException() {
        super();
    }

    public IllegalExpressionException(String message) {
        super(message);
    }

    public IllegalExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalExpressionException(Throwable cause) {
        super(cause);
    }
}
