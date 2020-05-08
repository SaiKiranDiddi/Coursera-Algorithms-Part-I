import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final double[] fraction;
    private double meanVal, stddevVal;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException("arguments must be positive");
        fraction = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int ind = StdRandom.uniform(1, n * n + 1);
                int row = 1 + (ind - 1) / n, col = 1 + ((ind - 1) % n);
                if (!grid.isOpen(row, col)) {
                    grid.open(row, col);
                }
            }
            fraction[i] = grid.numberOfOpenSites() * 1.0 / (n * n);
        }
        meanVal = StdStats.mean(fraction);
        stddevVal = StdStats.stddev(fraction);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddevVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanVal - CONFIDENCE * stddevVal / Math.sqrt(fraction.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanVal + CONFIDENCE * stddevVal / Math.sqrt(fraction.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = ["
                + stats.confidenceLo() + ", "
                + stats.confidenceHi() + "]");
    }
}

