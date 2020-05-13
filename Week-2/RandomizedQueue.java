import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int count;

    // construct an empty randomized queue
//    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        count = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (count == arr.length) {
            resize(count * 2);
            arr[count++] = item;
        } else {
            arr[count++] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0) {
            throw new NoSuchElementException("No item to remove");
        }
        Item ret;
        int index = StdRandom.uniform(0, count);
        ret = arr[index];
        arr[index] = arr[--count];
        if (count < (arr.length) / 4) {
            resize(arr.length / 2);
        }
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count == 0) {
            throw new NoSuchElementException("No item to remove");
        }
        Item ret;
        int index = StdRandom.uniform(0, count);
        ret = arr[index];
        return ret;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private Item[] copiedArray;
        private int count1;

        //        @SuppressWarnings("unchecked")
        RandomizedIterator() {
            copiedArray = (Item[]) new Object[count];
            System.arraycopy(arr, 0, copiedArray, 0, count);
            count1 = count;
        }

        public boolean hasNext() {
            return count1 != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element left");
            }
            int index = StdRandom.uniform(0, count1);
            Item ret = copiedArray[index];
            copiedArray[index] = copiedArray[count1 - 1];
            count1--;
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove op is not supported for now");
        }

    }

    //    @SuppressWarnings("unchecked")
    private void resize(int len) {
        Item[] temp = (Item[]) new Object[len];
        System.arraycopy(arr, 0, temp, 0, len / 2);
        arr = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Nothing here
    }

}
