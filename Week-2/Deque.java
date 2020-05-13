import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node left;
        Node right;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node head, tail;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (size == 0) {
            head = new Node(item);
            head.left = null;
            head.right = null;
            tail = head;
        } else {
            Node temp = new Node(item);
            temp.left = null;
            temp.right = head;
            head.left = temp;
            head = temp;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == 0) {
            head = new Node(item);
            head.left = null;
            head.right = null;
            tail = head;
        } else {
            Node temp = new Node(item);
            temp.left = tail;
            temp.right = null;
            tail.right = temp;
            tail = temp;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty queue");
        }
        Item ret = head.item;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node temp = head.right;
            temp.left = null;
            head = temp;
        }
        size--;
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty queue");
        }
        Item ret = tail.item;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node temp = tail.left;
            temp.right = null;
            tail = temp;
        }
        size--;
        return ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        public DequeIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element left");
            }
            Item item = current.item;
            current = current.right;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove op is not supported for now");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
//        Nothing here
        Deque<Integer> Q = new Deque<>();
        Q.addFirst(1);
        Q.addFirst(2);
        System.out.println(Q.removeLast());
        System.out.println(Q.removeLast());
        System.out.println(Q.size());

    }
}


