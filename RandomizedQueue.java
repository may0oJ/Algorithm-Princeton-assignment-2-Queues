import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("");
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int k = StdRandom.uniform(0, n);
        Item item = a[k];
        a[k] = a[n - 1];
        a[n - 1] = null;
        n--;
        if (n > 0 && n < a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = a[StdRandom.uniform(0, n)];
        return item;
    }

    /*
        public void shuffle(Item[] b) {
            Item[] temp = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                temp[i] = b[i];
            }
            StdRandom.shuffle(temp);
            for (int i = 0; i < n; i++) {
                b[i] = temp[i];
            }
        }
     */
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }


    private class ArrayIterator implements Iterator<Item> {
        private int i;
        private final int finalnum = n;
        private final Item[] temparr;

        private ArrayIterator() {
            temparr = (Item[]) new Object[finalnum];
            for (int j = 0; j < finalnum; j++) {
                temparr[j] = a[j];
            }
            StdRandom.shuffle(temparr);

        }

        public boolean hasNext() {
            return i < finalnum;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return temparr[i++];
        }
    }


    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> ranqtest = new RandomizedQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            ranqtest.enqueue(i);
        }
        for (int a : ranqtest)
            StdOut.println(a);

        for (int a : ranqtest) {
            for (int b : ranqtest)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
