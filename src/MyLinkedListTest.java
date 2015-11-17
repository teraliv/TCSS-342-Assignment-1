import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyLinkedListTest {

    private static ArrayList<Integer> array;

    private static int listLength;

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();

        readValues(list, args[0]);


        Sorting sort;

//        sort.bubbleSort(list.getHead());




        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileOutputStream("output.txt"));

            sort = new Sorting(list.size(), writer);
            sort.shellSort(list.getHead());


        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (writer != null)
                writer.close();
        }




    }


    private static void printList(Node head) {
        Node current = head.getNext();

        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }


    private static void readValues(MyLinkedList list, String inFile) {

        Scanner scanner = null;


        try {
            scanner = new Scanner(new FileInputStream(inFile));

            while (scanner.hasNext()) {

                int data = scanner.nextInt();
                list.add(data);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("En error occured while opening the file " + ex.getMessage());

        } finally {
            if (scanner == null) {
                scanner.close();
            }
        }
    }

 }
