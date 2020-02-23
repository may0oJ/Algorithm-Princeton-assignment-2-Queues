import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        try {
            String value = StdIn.readString();
            while (value != null) {
                randomizedQueue.enqueue(value);
                value = StdIn.readString();
            }
        }
        catch (NoSuchElementException e) {
            // StdOut.println(e.toString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
