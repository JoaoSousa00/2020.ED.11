package Exceptions;

/**
 * <p>
 * <strong>Author: </strong><br>
 * Joao Sousa<br>
 * <strong>Description: </strong><br>
 * Exception that
 * </p>
 */
public class CurrentModificationException extends Exception {

    /**
     * Creates a new instance of <code>CurrentModificationException</code> without detail message.
     */
    public CurrentModificationException() {
    }


    /**
     * Constructs an instance of <code>CurrentModificationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CurrentModificationException(String msg) {
        super(msg);
    }
}
