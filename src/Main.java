/*
 *  Main.java
 *  TCSS 342 - Autumn 2015
 *
 *  Assignment 1 - Implementing Linked List and sort it using Buuble & Shell Sort.
 *  Alex Terikov (teraliv@uw.edu)
 *  10/20/15
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for linked list and sorting algorithms.
 *
 * @author Alex Terikov (teraliv@uw.edu)
 * @version 10/20/15
 */
public class Main {

    // The size of the linked list.
    private static int listLength;


    public static void main(String[] args) {

        LinkedList list;
        Sorting sort;
        PrintWriter writer = null;


        try {
            writer = new PrintWriter(new FileOutputStream("output.txt"));

            // work with shell sort
            list = new LinkedList();
            readValues(list, args[0]);
            sort = new Sorting(list.size(), writer);
            sort.shellSort(list.getHead());

            // work with bubble sort
            list = new LinkedList();
            readValues(list, args[0]);
            sort = new Sorting(list.size(), writer);
            sort.bubbleSort(list.getHead());

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * Prints a linked list to console.
     *
     * @param head the reference to the first node.
     */
    private static void printList(Node head) {
        Node current = head.getNext();

        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Reads integers from the given file and populates linked list.
     *
     * @param list the linked list to add integers.
     * @param inFile the file with data.
     */
    private static void readValues(LinkedList list, String inFile) {

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
