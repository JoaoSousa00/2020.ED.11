package Lists;


import Exceptions.CurrentModificationException;
import Exceptions.EmptyArrayException;
import Interfaces.ListADT;
import Exceptions.NotFoundException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 *
 * @param <T>
 */
public class ArrayListADT<T> implements ListADT<T>, Iterable<T> {

    private static final int DEFAULT_CAPACITY = 100;
    private static final int NOT_FOUND = -1;

    protected T[] list;
    protected int rear;
    protected int modcount;

    private class BasicIterator implements Iterator {

        private int expectedModcount;
        private int count;

        public BasicIterator() {
            expectedModcount = modcount;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            try {
                if (expectedModcount != modcount) {
                    throw new CurrentModificationException("Erro - Ali");
                }
            } catch (CurrentModificationException ex) {
            }
            return (list[count] != null);
        }

        @Override
        public T next() {
            try {
                if (expectedModcount != modcount) {
                    throw new CurrentModificationException("Erro - Ali");
                }
            } catch (CurrentModificationException ex) {
            }
            return (T) list[count++];
        }

        @Override
        public void remove() {
            if(!hasNext()) {
                throw new Error();
            }
            try {
                ArrayListADT.this.remove(next());
            } catch (NotFoundException | EmptyArrayException ex) {
                Logger.getLogger(ArrayListADT.class.getName()).log(Level.SEVERE, null, ex);
            }
            expectedModcount++;
        }
        
    }

    /**
     * Creates a empty ArrayList
     */
    public ArrayListADT() {
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
        this.rear = -1;
        this.modcount = 0;
    }

    /**
     * Creates a empty ArrayList with a certain size
     *
     * @param size size of the ArrayList to be created
     */
    public ArrayListADT(int size) {
        this.list = (T[]) new Object[size];
        this.rear = -1;
        this.modcount = 0;
    }

    private int find(T target) {
        int count = 0;
        boolean found = false;
        while (count < size() && !found) {
            if (target == list[count]) {
                found = true;
            } else {
                count++;
            }
        }
        if (!found) {
            count = NOT_FOUND;
        }
        return (count);
    }

    @Override
    public T removeFirst() throws EmptyArrayException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        T removed = list[0];
        int i;
        for (i = 0; i < size() - 1; i++) {
            list[i] = list[i + 1];
        }

        list[rear] = null;
        rear--;
        modcount++;

        return removed;
    }

    @Override
    public T removeLast() throws EmptyArrayException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        T removed = list[rear];
        list[rear] = null;
        rear--;
        modcount++;

        return removed;
    }

    @Override
    public T remove(T element) throws EmptyArrayException, NotFoundException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        int i = find(element);
        if (i == NOT_FOUND) {
            throw new NotFoundException();
        }
        while (i < size() - 1) {
            list[i] = list[i + 1];
            i++;
        }
        list[rear] = null;
        rear--;
        modcount++;

        return element;
    }

    @Override
    public T first() throws EmptyArrayException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        return list[0];
    }

    @Override
    public T last() throws EmptyArrayException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        return list[rear];
    }

    @Override
    public boolean contains(T target) {
        return (find(target) != NOT_FOUND);
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return (rear + 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    @Override
    public String toString() {
        String result = "\t ToString";
        for (int i = 0; i < size(); i++) {
            result += ("\n" + list[i]);
        }
        return result;
    }

    /**
     * Expands the capacity of the array adding the DEFAULT_CAPACITY.
     */
    protected void expandCapacity() {
        T[] newArray = (T[]) new Object[size() + DEFAULT_CAPACITY];
        System.arraycopy(list, 0, newArray, 0, size());
        list = newArray;
    }

}
