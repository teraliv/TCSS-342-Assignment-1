/*
 *  LinkedList.java
 *  TCSS 342 - Autumn 2015
 *
 *  Assignment 1 - Implementing Linked List and sort it using Buuble & Shell Sort.
 *  Alex Terikov (teraliv@uw.edu)
 *  10/20/15
 */


/**
 * Creates a linked list.
 *
 * @author Alex Terikov (teraliv@uw.edu)
 * @version 10/20/15
 */
public class LinkedList {

    // The first node of a linke list.
    private Node head;

    // The length of a linked list.
    private int length;

    /**
     * Constructs a new linked list with a dummy node.
     */
    public LinkedList() {
        head = new Node(null, null);
        length = 0;
    }

    /**
     * Adds a new node to the tail of a linked list.
     *
     * @param newData the data to be stored in the node.
     */
    public void add(Integer newData) {

        // if my list is empty
        if (head.getNext() == null) {

            //assign my first node
            head.setNext(new Node(newData, null));
            length++;

        } else {
            // if the list is not empty, the last element needs to be find
            Node current = head.getNext();
            
            // loop until the last node is foud
            while (current.getNext() != null) {
                // move current to the next node
                current = current.getNext();
            }

            // set the next element to a new node
            //current.next = new Node(newData, null);
            current.setNext(new Node(newData, null));
            length++;
        }

    }

    /**
     * Gets the size of a linked list.
     *
     * @return the size of a linked list.
     */
    public int size() {
        return length;
    }

    /**
     * Gets a reference to the first node.
     *
     * @return first node.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Prints linked list.
     */
    public void printList() {
        Node current = head.getNext();

        while (current != null) {
            System.out.printf("%d \n", current.getData());
            current = current.getNext();
        }
    }
}
