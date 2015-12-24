/*
 *  Sorting.java
 *  TCSS 342 - Autumn 2015
 *
 *  Assignment 1 - Implementing Linked List and sort it using Buuble & Shell Sort.
 *  Alex Terikov (teraliv@uw.edu)
 *  10/20/15
 */

import java.io.PrintWriter;

/**
 * The class with sorting algorithms.
 *
 * @author Alex Terikov (teraliv@uw.edu)
 * @version 10/20/15
 */
public class Sorting {

    private static int pass;
    private static int cmp;
    private static int exch;
    private static int length;

    private static int totalPass;
    private static int totalCmp;
    private static int totalExch;

    private static long duration;
    private static PrintWriter writer;


    /**
     * Constructs a sort object and initializes values.
     *
     * @param theLength the length of a linked list.
     * @param theWriter the writer object to write data to a file.
     */
    public Sorting(int theLength, PrintWriter theWriter) {
        length = theLength;
        pass = 0;
        cmp = 0;
        exch = 0;

        totalPass = 0;
        totalCmp = 0;
        totalExch = 0;

        duration = 0;
        writer = theWriter;
    }

    /**
     * Sort a linked list using Bubble Sort algorithm.
     *
     * @param head the reference to the first node.
     */
    public void bubbleSort(Node head) {

        Node previous;   // Previous element of the current
        Node current;    // Current element to compare
        Node comparable; // Next element to compare

        boolean swapped = true;

        bubblePrintAndWriteResults(head, false);

        // start time to calculate sorting time in milli seconds
        long startTime = System.nanoTime();

        // Check if none of the element have been swaped
        // Indicates that the list is sorted.
        while (swapped) {
            cmp = 0;
            exch = 0;

            previous = head;
            current = head.getNext();
            comparable = head.getNext().getNext();
            swapped = false;

            while (comparable != null) {
                cmp++;

                // swap compared nodes
                if (current.getData() > comparable.getData()) {

                    bubbleSwapNodes(current, comparable, previous);
                    current = previous.getNext();
                    comparable = previous.getNext().getNext();

                    exch++;
                    swapped = true;

                }

                // move nodes to the next positions
                previous = previous.getNext();
                current = current.getNext();
                comparable = comparable.getNext();
            }

            pass++;
            totalPass++;
            totalExch += exch;
            totalCmp += cmp;

            if (exch != 0) {
                bubblePrintAndWriteResults(head, false);
            }
        }
        // end time to calculate sorting time in milli seconds
        long endTime = System.nanoTime();

        // sorting time in milli seconds
        duration = (endTime - startTime) / 1000000;

        bubblePrintAndWriteResults(head, true);
    }

    /**
     * Sort a linked list using Shell Sort algorithm.
     *
     * @param head the reference to the first node.
     */
    public void shellSort(Node head) {

        resetCounts();

        Node current;    // Current element to compare
        Node comparable; // Next element to compare
        Node lprev;      // Previous node of the current
        Node rprev;      // Previous node of the comparable

        boolean swapped = true;

        int index = 0;
        int extra = 0;
        double k = 0;

        long startTime = System.nanoTime();

        // find the proper K value
        while (((Math.pow(3, index) - 1) / 2) < length) {
            k = (Math.pow(3, index) - 1) / 2;
            ++index;
        }
        index--;

        shellPrintAndWriteResults(head, k, extra, false);

        // compare elements with the K value larger than 1
        while (k > 1) {

            swapped = true;

            current = head.getNext();
            comparable = head.getNext();
            lprev = head;
            rprev = head;

            for (int i = 0; i < k; i++) {
                rprev = comparable;
                comparable = comparable.getNext();
            }

            extra = length - (int) k;

            // Check if none of the element have been swapped
            // Indicates that the list is sorted for the given K.
            while (swapped) {
                swapped = false;

                while (comparable != null) {
                    cmp++;

                    // compare two numbers: current > comparable
                    if (current.getData() > comparable.getData()) {

                        shellSwapNodes(current, comparable, lprev, rprev);

                        // move nodes to the next position
                        lprev = lprev.getNext();
                        rprev = rprev.getNext();
                        current = lprev.getNext();
                        comparable = rprev.getNext();

                        swapped = true;
                        exch++;
                    }

                    // move nodes to the next positions
                    else {
                        lprev = lprev.getNext();
                        rprev = rprev.getNext();
                        current = lprev.getNext();
                        comparable = rprev.getNext();
                    }
                }
                pass++;

                // move the list back to the beginning
                current = head.getNext();
                comparable = head.getNext();
                lprev = head;
                rprev = head;

                // set comparable to N nodes ahead
                for (int i = 0; i < k; i++) {
                    rprev = comparable;
                    comparable = comparable.getNext();
                }

            }

            shellPrintAndWriteResults(head, k, extra, false);

            pass -= 1;
            totalPass += pass;
            totalCmp += cmp-extra;
            totalExch += exch;

            // make k value lower
            k = (Math.pow(3, --index) - 1) / 2;
            extra = length - (int) k;

        }

        // for K value equal to 1, just do bubble sort with a helper method.
        if (k == 1) {
            shellBubbleSort(head, extra, startTime);
        }

    }


    /**
     * Swap the given nodes for the Shell Sort algorithm.
     *
     * @param current the current node to compare
     * @param comparable the next element to compare with current.
     * @param lprev the previous element of the current.
     * @param rprev the previous element of comparable.
     */
    private void shellSwapNodes(Node current, Node comparable, Node lprev, Node rprev) {
        rprev.setNext(comparable.getNext());
        comparable.setNext(current.getNext());
        lprev.setNext(comparable);
        current.setNext(rprev.getNext());
        rprev.setNext(current);
    }

    /**
     * Swap the given nodes for the Bubble Sort algorithm.
     * @param current the current node to compare
     * @param comparable the next element to compare with current.
     * @param prev the previous element of the current.
     */
    private void bubbleSwapNodes(Node current, Node comparable, Node prev) {
        current.setNext(comparable.getNext());
        comparable.setNext(current);
        prev.setNext(comparable);
    }

    /**
     * Reset static counts variables.
     */
    private void resetCounts() {
        pass = 0;
        cmp = 0;
        exch = 0;

        totalPass = 0;
        totalCmp = 0;
        totalExch = 0;

        duration = 0;
    }

    /**
     * Helper method for the shell sort algorithm for the final stage.
     *
     * @param head a reference to the first node.
     * @param extra an extra iteration value when list is sorted.
     * @param startTime start time to calculate sorting time
     */
    private void shellBubbleSort(Node head, int extra, long startTime) {

        pass = 0;
        cmp = 0;
        exch = 0;

        int k = 1;

        boolean swapped = true;

        Node prev;
        Node current;
        Node comparable;

        while (swapped) {

            prev = head;
            current = head.getNext();
            comparable = head.getNext().getNext();
            swapped = false;

            while (comparable != null) {

                cmp++;

                if (current.getData() > comparable.getData()) {

                    bubbleSwapNodes(current, comparable, prev);

                    current = prev.getNext();
                    comparable = prev.getNext().getNext();
                    swapped = true;
                    exch++;
                }

                // move nodes to the next positions
                prev = prev.getNext();
                current = current.getNext();
                comparable = comparable.getNext();
            }
            pass++;

        }

        pass -= 1;
        totalPass += pass;
        totalCmp += (cmp - extra);
        totalExch += exch;

        long endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;

        shellPrintAndWriteResults(head, k, extra, true);
    }

    /**
     * Helper method for Shell Sort algorithm to print and writ output of the intermediate stages.
     *
     * @param head a reference to the first node.
     * @param complete trigger to print total results.
     */
    private void bubblePrintAndWriteResults(Node head, boolean complete) {

        if (pass == 0) {
            if (length <= 20) {
                System.out.printf("\nBUBBLE SORT RESULTS\n");
                System.out.printf("===================\n");
                System.out.printf("        pass       cmp      exch      time   ");
                System.out.printf("%-80s\n", head.getNext().toString());

                writer.printf("\nBUBBLE SORT RESULTS\n");
                writer.printf("===================\n");
                writer.printf("        pass       cmp      exch      time   ");
                writer.printf("%-80s\n", head.getNext().toString());
            } else {
                System.out.printf("\nBUBBLE SORT RESULTS\n");
                System.out.printf("===================\n");
                System.out.printf("      pass       cmp      exch      time    \n");

                writer.printf("\nBUBBLE SORT RESULTS\n");
                writer.printf("===================\n");
                writer.printf("      pass       cmp      exch      time    \n");
            }
            System.out.println("------------------------------------------");
            writer.println("------------------------------------------");

        } else {
            if (length <= 20) {
                System.out.printf("%9d %10d %8d %14s %-65s\n", pass, cmp, exch, " ", head.getNext().toString());
                writer.printf("%9d %10d %8d %14s %-65s\n", pass, cmp, exch, " ", head.getNext().toString());
            }

        }

        // print and write totals
        if (complete) {
            if (length <= 20)
                System.out.println("------------------------------------------");
            System.out.printf("Total %3d %10d %8d %8d ms\n\n", totalPass, totalCmp, totalExch, duration);
            if (length <= 20)
                writer.println("------------------------------------------");
            writer.printf("Total %3d %10d %8d %8d ms\n\n", totalPass, totalCmp, totalExch, duration);
        }

    }

    /**
     * Helper method for Shell Sort algorithm to print and writ output of the intermediate stages.
     *
     * @param head a reference to the first node.
     * @param complete trigger to print total results.
     */
    private void shellPrintAndWriteResults(Node head, double k, int shift, boolean complete) {

        if (pass == 0) {

            if (length <= 20) {
                System.out.printf("SHELL SORT RESULTS\n");
                System.out.printf("===================\n");
                System.out.printf("      k  pass       cmp      exch      time    ");
                System.out.printf("%-84s\n", head.getNext().toString());

                writer.printf("\nSHELL SORT RESULTS\n");
                writer.printf("===================\n");
                writer.printf("      k  pass       cmp      exch      time    ");
                writer.printf("%-84s\n", head.getNext().toString());
            }
            else {
                System.out.printf("SHELL SORT RESULTS\n");
                System.out.printf("===================\n");
                System.out.printf("      k  pass       cmp      exch      time    \n");

                writer.printf("\nSHELL SORT RESULTS\n");
                writer.printf("===================\n");
                writer.printf("      k  pass       cmp      exch      time    \n");
            }
            System.out.println("-------------------------------------------");
            writer.println("-------------------------------------------");

        } else {
            for (int i = 1; i < pass; i++) {

                if (length <= 20) {
                    if (i == (pass - 1)) {
                        System.out.printf("%7d %3d %10d %8d %14s %-65s\n", (int) k, i, cmp - shift, exch, " ", head.getNext().toString());
                        writer.printf("%7d %3d %10d %8d %14s %-65s\n", (int) k, i, cmp - shift, exch, " ", head.getNext().toString());
                    }
                    else {
                        System.out.printf("%7d %3d %34s %-85s\n", (int) k, i, " ", head.getNext().toString());
                        writer.printf("%7d %3d %34s %-85s\n", (int) k, i, " ", head.getNext().toString());
                    }

                } else {

                    if (i == (pass - 1)) {
                        System.out.printf("%7d %3d %10d %8d\n", (int) k, i, cmp - shift, exch);
                        writer.printf("%7d %3d %10d %8d\n", (int) k, i, cmp - shift, exch);
                    }
                }
            }
        }

        // print and write totals
        if (complete) {
            System.out.println("-------------------------------------------");
            System.out.printf("  Total %3d %10d %8d %8d ms\n\n", totalPass, totalCmp, totalExch, duration);

            writer.println("-------------------------------------------");
            writer.printf("  Total %3d %10d %8d %8d ms\n\n", totalPass, totalCmp, totalExch, duration);
        }
    }

}
