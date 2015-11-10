import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyLinkedListTest {

//    private static MyLinkedList list;
    private static ArrayList<Integer> array;

    public static void main(String[] args) {

//        array = new ArrayList<Integer>();



        MyLinkedList list = new MyLinkedList();



        
        readValues(list);
        int listLength = list.size();
        System.out.printf("List length: %d\n", list.size());

//        swapNodes(list.head);
//        bubbleSort(list.head);
//        list.head.setData(50);

//        list.head = list.head.next;



//        bubbleSort(list.head);
//        bubbleSort(list.head);
//        bubbleSort(list.head);
//        bubbleSort(list.head);
//        bubbleSort(list.head);

        // bubble sort
//        for (int i = 0; i < 99; i++) {
//            bubbleSort(list.head);
//        }

//        printList(list.head);

        shellSort(list.head);
//        printList(list.head);

    }

    private static void printList(Node head) {
        Node current = head.next;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    private static void bubbleSort(Node head) {

        Node current = head.next.next;
        Node temp = head.next.next;
        Node prev = head.next;

        while (current.next != null) {

            if (head.next.data > head.next.next.data) {
                current = head.next;
                current.next = current.next.next;
                temp.next = current;
                head.next = temp;

                prev = head.next;
//                current = head.next.next;
            }


            if (current.data > current.next.data) {
                temp = current.next;
                current.next = current.next.next;
                temp.next = current;
                prev.next = temp;
                current = temp;

            }

            prev = prev.next;
            current = current.next;

//            if (current.next == null) {
//                temp = current.next;
//                temp.next = current;
//                current.next = null;
//                prev.next = temp;
//            }
        }
    }


    private static void shellSort(Node head) {

        Node current = head.next; // current element to compare
        Node comparable = head.next; //next element to compare
        Node lprev = head; // left previous
        Node rprev = head; // right previous

        boolean swaped = true;

        int pass = 1;
        double k = 0;
        int cmp = 0;
        int exch = 0;
        int index = 0;


        while (((Math.pow(3, index) - 1) / 2) < 10) {
            k = (Math.pow(3, index) - 1) / 2;
            ++index;
        }
        index--;
        System.out.printf("k: %d, index: %d\n", (int) k, index);

        for (int i = 0; i < k; i++) {
            rprev = comparable;
            comparable = comparable.next;
        }

        System.out.printf("      k  pass       cmp      exch");
        System.out.printf("%64s\n", current.toString());
        System.out.println("----------------------------------");

        while (k > 1) {
            while (swaped) {

                while (comparable != null) {

                    cmp++;

                    // compare two numbers: current > comparable
                    if (current.data > comparable.data) {
                        rprev.next = comparable.next;
                        comparable.next = current.next;
                        lprev.next = comparable;
                        current.next = rprev.next;
                        rprev.next = current;

                        lprev = lprev.next;
                        rprev = rprev.next;
                        current = lprev.next;
                        comparable = rprev.next;

                        swaped = true;
                        exch++;
                    }

                    // move pointers to the next positions
                    else {
                        lprev = lprev.next;
                        rprev = rprev.next;
                        current = lprev.next;
                        comparable = rprev.next;

                        swaped = false;
                    }

                }

                pass++;

                // move the list back to the beginning
                current = head.next;
                comparable = head.next;
                lprev = head;
                rprev = head;

                // set comparable to N nodes ahead
                for (int i = 0; i < k; i++) {
                    rprev = comparable;
                    comparable = comparable.next;
                }
                //System.out.printf("%7d %3d %55s\n", k, pass, head.next.toString());
            }

            for (int i = 1; i < pass; i++) {
                if (i == (pass - 1))
                    System.out.printf("%7d %3d %10d %8d %65s\n", (int) k, i, cmp, exch, head.next.toString());
                else
                    System.out.printf("%7d %3d %85s\n",(int) k, i, head.next.toString());
            }

            // make k lower
            k = (Math.pow(3, --index) - 1) / 2;
            pass = 1;
            cmp = 0;
            exch = 0;
        }

        if (k == 1) {

            swaped = true;

            Node temp;
            Node prev;

            while (swaped) {

                current = head.next.next;
                temp = head.next.next;
                prev = head.next;

                while (current.next != null) {

                    cmp++;

                    if (head.next.data > head.next.next.data) {
                        current = head.next;
                        current.next = current.next.next;
                        temp.next = current;
                        head.next = temp;

                        prev = head.next;
                        exch++;
                        swaped = true;
                    }

                    if (current.data > current.next.data) {
                        temp = current.next;
                        current.next = current.next.next;
                        temp.next = current;
                        prev.next = temp;
                        current = temp;

                        exch++;
                        swaped = true;
                    }

                    prev = prev.next;
                    current = current.next;
                    swaped = false;
                    pass++;
                }
            }

            for (int i = 0; i < pass; i++) {
                if (i == (pass - 1))
                    System.out.printf("%7d %3d %10d %8d %65s\n", (int) k, i, cmp, exch, head.next.toString());
                else
                    System.out.printf("%7d %3d %85s\n",(int) k, i, head.next.toString());
            }

        }
    }



    private static void swapNodes(Node head) {
        Node current = head;

        current = head.next;
        head.next = current.next;
        current.next = head;
        head = current;
        current = current.next;
    }

    private static void swap(int a, int b, int index) {
        array.set(index+1, a);
        array.set(index, b);
    }

    private static void readValues(MyLinkedList list) {

        Scanner fileScanner = null;


        try {
            fileScanner = new Scanner(new FileInputStream("random10.txt"));

            while (fileScanner.hasNext()) {

                int data = fileScanner.nextInt();
                list.add(data);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("En error occured while opening the file " + ex.getMessage());

        } finally {
            if (fileScanner == null) {
                fileScanner.close();
            }
        }
    }

    //    private static void bubbleSort(Node head) {
//
//        for (int i = array.size(); i > 0; i--) {
//            for (int j = 0; j < 5; j++) {
//                if (array.get(j) > array.get(j+1)) {
//                    swap(array.get(j), array.get(j+1), j);
//                }
//            }
//        }
//    }
 }
