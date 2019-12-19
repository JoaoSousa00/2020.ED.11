package Lists;

import Interfaces.UnorderedListADT;



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
public class ArrayUnorderedList<T> extends ArrayListADT<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        for (int i = size(); i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[0] = element;
        rear++;
        modcount++;
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            expandCapacity();
        }
        list[size()] = element;
        rear++;
        modcount++;
    }

    @Override
    public void addToAfter(T element, T target) {
        if (size() == list.length) {
            expandCapacity();
        }
        Comparable<T> temp = (Comparable<T>) target;
        int i = 0;
        while (i < size() && temp.compareTo(list[i]) != 0) {
            i++;
        }
        for (int j = size(); j > i; j--) {
            list[j] = list[j - 1];
        }
        list[++i] = element;
        rear++;
        modcount++;
    }

}
