/**
 * Created by aterikov on 10/20/15.
 */
public class MyLinkedList {

    private Node head;
    private int length;

    public MyLinkedList() {
        head = new Node(null, null);
        length = 0;
    }

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

    public int size() {
        return length;
    }

    public Node getHead() {
        return head;
    }

    public void printList() {
        Node current = head.getNext();

        while (current != null) {
            System.out.printf("%d \n", current.getData());
            current = current.getNext();
        }
    }
}
