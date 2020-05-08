import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private final int size;
    private int openCount;
    private final WeightedQuickUnionUF quf, quf1;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        size = n;
        quf = new WeightedQuickUnionUF(size * size + 2);
        quf1 = new WeightedQuickUnionUF(size * size + 1);
        opened = new boolean[size][size];
        openCount = 0;
    }

    private int makeIndex(int r, int c) {
        return (r - 1) * size + c;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > size || col > size) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (opened[row - 1][col - 1]) return;
        opened[row - 1][col - 1] = true;
        openCount++;
        int ind = makeIndex(row, col);
        if (row == 1) {
            quf.union(ind, 0);
            quf1.union(ind, 0);
        }
        if (row == size) {
            quf.union(ind, size * size + 1);
        }
        if (row > 1 && opened[row - 2][col - 1]) {
            quf.union(ind, ind - size);
            quf1.union(ind, ind - size);
        }
        if (row < size && opened[row][col - 1]) {
            quf.union(ind, ind + size);
            quf1.union(ind, ind + size);
        }
        if (col > 1 && opened[row - 1][col - 2]) {
            quf.union(ind, ind - 1);
            quf1.union(ind, ind - 1);
        }
        if (col < size && opened[row - 1][col]) {
            quf.union(ind, ind + 1);
            quf1.union(ind, ind + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > size || col > size) {
            throw new IllegalArgumentException("n must be positive");
        }
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > size || col > size) {
            throw new IllegalArgumentException("arguments must be positive");
        }

        return isOpen(row, col) && (quf1.find(makeIndex(row, col)) == quf1.find(0));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return quf.find(0) == quf.find(size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Nothing special to be done here
    }
}
