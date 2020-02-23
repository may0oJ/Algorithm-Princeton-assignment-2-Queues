import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }


    // return the number of items on the deque
    public int size() {
        return n;
    }


    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        if (isEmpty()) {
            last = first;
            last.next = null;
        }
        else {
            first.next = oldfirst;
            oldfirst.previous = first;

        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("");
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
            first.previous = null;
        }
        else {
            oldlast.next = last;
            last.previous = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        n--;
        Item item = first.item;
        if (!isEmpty()) {
            first = first.next;
            first.previous = null;
        }
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        n--;
        Item item = last.item;
        if (!isEmpty()) {
            last = last.previous;
            last.next = null;
        }
        if (isEmpty()) {
            last = null;
            first = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dequetest = new Deque<>();
        for (int i = 0; i < 6; i++) {
            dequetest.addLast(i);
        }
        for (int i = 0; i < 6; i++) {
            dequetest.addFirst(i);
        }
        Iterator<Integer> iterator = dequetest.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");
        }
        StdOut.println("");
        for (int i = 0; i < 8; i++) {
            StdOut.print(dequetest.removeFirst() + " ");
            StdOut.print(dequetest.isEmpty() + " ");
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
        for (int i = 0; i < 4; i++) {
            StdOut.print(dequetest.removeLast() + " ");
            StdOut.print(dequetest.isEmpty() + " ");
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
        for (int i = 10; i < 15; i++) {
            dequetest.addLast(i);
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
        for (int i = 0; i < 5; i++) {
            StdOut.print(dequetest.removeFirst() + " ");
            StdOut.print(dequetest.isEmpty() + " ");
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
        for (int i = 10; i < 15; i++) {
            dequetest.addFirst(i);
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
        for (int i = 0; i < 5; i++) {
            StdOut.print(dequetest.removeLast() + " ");
            StdOut.print(dequetest.isEmpty() + " ");
            for (int a : dequetest)
                StdOut.print(a);
            StdOut.print(" " + dequetest.size());
            StdOut.println();
        }
    }
}
