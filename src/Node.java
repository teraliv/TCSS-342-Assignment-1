/**
 * Created by aterikov on 10/29/15.
 */
public class Node {

    private Integer data;
    private Node next;

    public Node(Integer theData, Node theNext) {
        next = theNext;
        data = theData;
    }

    public int getData() {
        return data;
    }

    public void setData(Integer theData) {
        data = theData;
    }

    public Node getNext() {
        return next;
    }

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
