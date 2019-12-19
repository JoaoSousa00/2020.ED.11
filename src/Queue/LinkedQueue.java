package Queue;

import Interfaces.QueueADT;
import Nodes.LinearNode;
import Exceptions.EmptyCollectionException;

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
 * Class that represents a LinkedQueue Collection
 * </p>
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode front;
    private LinearNode rear;
    private int count;

    /**
     * Creates a empty LinkedQueue
     */
    public LinkedQueue() {
        front = new LinearNode();
        rear = new LinearNode();
        count = 0;
    }

    @Override
    public void enqueue(T element) {
        LinearNode newNode = new LinearNode(element);
        if (isEmpty()) {
            front = (newNode);
            rear = (newNode);
        } else {
            rear.setNext(newNode);
            rear = rear.getNext();
        }
        count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        LinearNode savedNode = front;
        front = front.getNext();
        count--;
        return (T) savedNode.getElement();
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue");
        }
        return (T) (front.getElement());
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
        String result = "";
        LinearNode tmp = front;
        result += "\n\tToString\nFront: " + this.front.getElement();
        while (tmp != null) {
            result += "\n" + tmp.getElement().toString();
            tmp = tmp.getNext();
        }
        return result;
    }

}
