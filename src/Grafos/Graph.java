package Grafos;

import Exceptions.EmptyCollectionException;
import Interfaces.GraphADT;
import Lists.ArrayUnorderedList;
import Queue.LinkedQueue;
import Stacks.LinkedStack;
import java.util.Iterator;

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
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices

    /**
     * Creates an empty graph.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    protected int getIndex(T Vertex) {
        int result = 0;
        boolean found = false;
        Comparable temp = (Comparable) Vertex;
        for (int i = 0; (i < numVertices) && !found; i++) {
            if (temp.compareTo(this.vertices[i]) == 0) {
                found = true;
                result = i;
            }
        }
        if (!found) {
            result = -1;
        }
        return result;
    }

    protected boolean indexIsValid(int index) {
        return !(index == -1);
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        int index;
        if ((index = getIndex(vertex)) == -1) {

            for (int i = index; i < numVertices - 1; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices - 1; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int j = index; j < numVertices - 1; j++) {
                for (int i = 0; i < numVertices; i++) {
                    adjMatrix[i][j] = adjMatrix[i][j + 1];
                }
            }
            numVertices--;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        if (indexIsValid(getIndex(vertex1)) && indexIsValid(getIndex(vertex2))) {
            this.adjMatrix[getIndex(vertex1)][getIndex(vertex2)] = false;
            this.adjMatrix[getIndex(vertex2)][getIndex(vertex1)] = false;
        }
    }

    /**
     * Returns an iterator that performs a breadth(largura) first search
     * traversal starting at the given index.
     *
     * @param startVertex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     * @throws Exceptions.EmptyCollectionException
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (!indexIsValid(getIndex(startVertex))) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(getIndex(startVertex)));
        visited[getIndex(startVertex)] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a depth(profundidade) first search
     * traversal starting at the given index.
     *
     * @param startVertex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     * @throws Exceptions.EmptyCollectionException
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(getIndex(startVertex))) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(getIndex(startVertex)));
        resultList.addToRear(vertices[getIndex(startVertex)]);
        visited[getIndex(startVertex)] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException {
        Iterator it = iteratorBFS(this.vertices[0]);
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i == numVertices;

    }

    @Override
    public int size() {
        return numVertices;
    }

    protected void expandCapacity() {
        boolean[][] novoadj = new boolean[numVertices + DEFAULT_CAPACITY][numVertices + DEFAULT_CAPACITY];
        T[] novovert = (T[]) (new Object[numVertices + DEFAULT_CAPACITY]);

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(this.adjMatrix, 0, novoadj, 0, numVertices);
        }
        this.adjMatrix = novoadj;

        System.arraycopy(this.vertices, 0, novovert, 0, numVertices);
        this.vertices = novovert;
    }

}
