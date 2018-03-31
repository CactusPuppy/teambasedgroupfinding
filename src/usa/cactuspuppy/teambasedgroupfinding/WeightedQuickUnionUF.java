package usa.cactuspuppy.teambasedgroupfinding;

import java.util.ArrayList;

import static java.lang.System.arraycopy;

/**
 * Code from Princeton Library.
 * Modified to allow for dynamic resizing
 * Assumes that new overflow elements are added sequentially (i.e. next addition is index n)
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author CactusPuppy
 */
public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    private static final int INIT_SIZE = 8;
    private static final double MIN_LF = 0.25;

    /**
     * Initializes an empty union–find data structure with {@code INIT_SIZE} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     */
    public WeightedQuickUnionUF() {
        count = INIT_SIZE;
        parent = new int[INIT_SIZE];
        size = new int[INIT_SIZE];
        for (int i = 0; i < INIT_SIZE; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        if (p < 0 || q < 0) {
            throw new IllegalArgumentException("P and Q must be positive");
        }
        if (p >= parent.length || q >= parent.length) {
            return false;
        }
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        if (p >= parent.length || q >= parent.length) {
            int[] newParent = new int[parent.length * 2];
            int[] newSize = new int[size.length * 2];
            arraycopy(parent, 0, newParent, 0, parent.length);
            arraycopy(size, 0, newSize, 0, size.length);
            for (int i = 0; i < parent.length; i++) {
                newParent[i + parent.length] = i + parent.length;
                size[i + size.length] = 1;
            }
            parent = newParent;
            size = newSize;
        }
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    /**
     * Unit test
     * @param args irrelevant
     */
    public static void main(String[] args) {
        WeightedQuickUnionUF test = new WeightedQuickUnionUF();
        test.union(2, 3);
        System.out.println("Check basic union: " + test.connected(3, 2));
        System.out.println("Check not connected within bounds: " + !test.connected(5, 2));
        System.out.println("Check not connected out of bounds: " + !test.connected(18, 2));
        test.union(4, 5);
        System.out.println("Check two size 2 components not connected: " + !test.connected(5, 3));
    }
}
