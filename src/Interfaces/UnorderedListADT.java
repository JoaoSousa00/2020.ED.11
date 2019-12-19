package Interfaces;

/**
 * <p>
 * <strong>Author: </strong><br>
 * Joao Sousa<br>
 * <strong>Description: </strong><br>
 * Interface that
 * </p>
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to the front of this list
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the rear of this list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element after the target element in this list
     *
     * @param element the element to be added to this list
     * @param target the element that will be the previous of the element to be
     * added
     */
    public void addToAfter(T element, T target);
}
