/*
 *  Node.java
 *  TCSS 342 - Autumn 2015
 *
 *  Assignment 1 - Implementing Linked List and sort it using Buuble & Shell Sort.
 *  Alex Terikov (teraliv@uw.edu)
 *  10/20/15
 */


/**
 * Single linked list node to store integer data.
 *
 * @author Alex Terikov (teraliv@uw.edu)
 * @version 10/20/15
 */
public class Node {

    // The node's data
    private Integer data;

    // The reference to the next node
    private Node next;

    /**
     * Constructs a new node with the give data and a reference to the next node.
     *
     * @param theData - the data to be stored in a node.
     * @param theNext - the reference to the next node.
     */
    public Node(Integer theData, Node theNext) {
        next = theNext;
        data = theData;
    }

    /**
     * Gets the method of a node.
     *
     * @return the node's data.
     */
    public int getData() {
        return data;
    }

    public void setData(Integer theData) {
        data = theData;
    }

    /**
     * Gets the reference to the next node.
     *
     * @return reference to the next node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param theNext reference to the node.
     */
    public void setNext(Node theNext) {
        next = theNext;
    }

    @Override
    public String toString() {
        //return "Node [data = " + data + ", next = " + next + "]";

        String result = data + " ";

        if (next != null) {
            result += next;
        }
        return result;
    }

}
