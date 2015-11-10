/**
 * Created by aterikov on 10/20/15.
 */
public class MyLinkedList {

    public Node head;
    private int length;

    public MyLinkedList() {
        head = new Node(null, null);
        length = 0;
    }

    public void add(Integer newData) {

        // if my list is empty
        if (head.next == null) {
            //assign my first node
            head.next = new Node(newData, null);
            length++;

        } else {
            // if the list is not empty, the last element needs to be find
            Node current = head.next;
            
            // loop until the last node is foud
            while (current.next != null) {
                // move current to the next node
                current = current.next;
            }

            // set the next element to a new node
            current.next = new Node(newData, null);
            length++;
        }

    }

    public int size() {
        return length;
    }

    public void printList() {
        Node current = head.next;

        while (current != null) {
            System.out.printf("%d \n", current.data);
            current = current.next;
        }
    }
}
