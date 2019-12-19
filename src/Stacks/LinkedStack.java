package Stacks;

import Exceptions.EmptyCollectionException;
import Interfaces.StackADT;
import Nodes.LinearNode;

/**
 * <h3>
 * ESTG - Escola Superior de Tecnologia e Gestão<br>
 * IPP - Instituto Politécnico do Porto<br>
 * LEI - Licenciatura em Engenharia Informática<br>
 * PP - Paradigmas de Programação<br>
 * </h3>
 * <p>
 * <strong>Author: </strong><br>
 * Joao Sousa<br>
 * <strong>Description: </strong><br>
 * Class that represents
 * </p>
 */
public class LinkedStack<T> implements StackADT<T> {

    /**
     * int that represents the number of elements in the array
     */
    private int count;

    /**
     * reference to the first node in list
     */
    private LinearNode<T> top;

    /**
     * Creates an empty stack.
     */
    public LinkedStack() {
        this.count = 0;
        this.top = new LinearNode<>();
    }

    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (count == 0) {
            top.setNext(newNode);
            newNode.setNext(null);
        } else {
            newNode.setNext(top.getNext());
            top.setNext(newNode);
        }

        count++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        count--;
        LinearNode tmp = top.getNext();
        top.setNext(top.getNext().getNext());
        tmp.setNext(null);
        return (T) tmp.getElement();
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack");
        }
        return top.getNext().getElement();
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty List.";
        }
        LinearNode<T> tmp = top.getNext();
        String string = "Lista:";
        while (tmp != null) {
            string += ("\n" + tmp.getElement().toString());
            tmp = tmp.getNext();
        }
        return string;
    }

}