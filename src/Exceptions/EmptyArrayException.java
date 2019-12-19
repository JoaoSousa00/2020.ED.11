package Exceptions;

/**
 * <p>
 * <strong>Author: </strong><br>
 * Joao Sousa<br>
 * <strong>Description: </strong><br>
 * Exception that
 * </p>
 */
public class EmptyArrayException extends Exception {

    /**
     * Creates a new instance of <code>EmptyArrayException</code> without detail message.
     */
    public EmptyArrayException() {
    }


    /**
     * Constructs an instance of <code>EmptyArrayException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EmptyArrayException(String msg) {
        super(msg);
    }
}
