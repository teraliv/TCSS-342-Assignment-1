import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by aterikov on 11/15/15.
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


    public void bubbleSort(Node head) {

        Node previous;
        Node current;
        Node comparable;

        boolean swapped = true;

        while (swapped) {

            previous = head;
            current = head.getNext();
            comparable = head.getNext().getNext();
            swapped = false;

            while (comparable != null) {

                if (current.getData() > comparable.getData()) {
                    bubbleSwapNodes(current, comparable, previous);
                    current = previous.getNext();
                    comparable = previous.getNext().getNext();
                    swapped = true;
                }

                // move nodes to the next positions
                previous = previous.getNext();
                current = current.getNext();
                comparable = comparable.getNext();
            }
        }
    }


    public void shellSort(Node head) {

        long startTime = System.nanoTime();

        Node current;    // Current element to compare
        Node comparable; // Next element to compare
        Node lprev;      // Previous node of the current
        Node rprev;      // Previous node of the comparable

        boolean swapped = true;

        int index = 0;
        int extra = 0;
        double k = 0;

        // find the proper K value
        while (((Math.pow(3, index) - 1) / 2) < length) {
            k = (Math.pow(3, index) - 1) / 2;
            ++index;
        }
        index--;

        printAndWriteResults(head, k, extra, false);

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

            printAndWriteResults(head, k, extra, false);

            pass -= 1;
            totalPass += pass;
            totalCmp += cmp-extra;
            totalExch += exch;

            // make k value lower
            k = (Math.pow(3, --index) - 1) / 2;
            extra = length - (int) k;

        }

        if (k == 1) {
            shellBubbleSort(head, extra, startTime);
        }

    }


    private void shellSwapNodes(Node current, Node comparable, Node lprev, Node rprev) {

        rprev.setNext(comparable.getNext());
        comparable.setNext(current.getNext());
        lprev.setNext(comparable);
        current.setNext(rprev.getNext());
        rprev.setNext(current);
    }


    private void bubbleSwapNodes(Node current, Node comparable, Node prev) {

        current.setNext(comparable.getNext());
        comparable.setNext(current);
        prev.setNext(comparable);
    }

    private void shellNextPosition(Node lprev, Node rprev, Node current, Node comparable) {

        lprev = lprev.getNext();
        rprev = rprev.getNext();
        current = lprev.getNext();
        comparable = rprev.getNext();
    }


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

        printAndWriteResults(head, k, extra, true);
    }


    private void printAndWriteResults(Node head, double k, int shift, boolean complete) {

        if (pass == 0) {

            if (length <= 20) {
                System.out.printf("      k  pass       cmp      exch");
                System.out.printf("%64s\n", head.getNext().toString());

                writer.printf("      k  pass       cmp      exch");
                writer.printf("%64s\n", head.getNext().toString());
            }
            else {
                System.out.printf("      k  pass       cmp      exch\n");
                writer.printf("      k  pass       cmp      exch\n");
            }
            System.out.println("----------------------------------");
            writer.println("----------------------------------");

        } else {
            for (int i = 1; i < pass; i++) {

                if (length <= 20) {
                    if (i == (pass - 1)) {
                        System.out.printf("%7d %3d %10d %8d %65s\n", (int) k, i, cmp - shift, exch, head.getNext().toString());
                        writer.printf("%7d %3d %10d %8d %65s\n", (int) k, i, cmp - shift, exch, head.getNext().toString());
                    }
                    else {
                        System.out.printf("%7d %3d %85s\n", (int) k, i, head.getNext().toString());
                        writer.printf("%7d %3d %85s\n", (int) k, i, head.getNext().toString());
                    }

                } else {

                    if (i == (pass - 1)) {
                        System.out.printf("%7d %3d %10d %8d\n", (int) k, i, cmp - shift, exch);
                        writer.printf("%7d %3d %10d %8d\n", (int) k, i, cmp - shift, exch);
                    }
                }
            }
        }

        // print totals
        if (complete) {
            System.out.println("----------------------------------");
            System.out.printf("  Total %3d %10d %8d\n", totalPass, totalCmp, totalExch);
            System.out.printf("   Time %d ms\n", duration);

            writer.println("----------------------------------");
            writer.printf("  Total %3d %10d %8d\n", totalPass, totalCmp, totalExch);
            writer.printf("   Time %d ms", duration);
        }
    }

}
