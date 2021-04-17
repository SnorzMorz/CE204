package labs.lab7;

import graphs.*;

import java.util.*;

/***********************************************************************
 *  Lab 7, exercise 1                                                  *
 **********************************************************************/

public class Kruskal {
    /*
     *  To use the class, call Kruskal.minimumSpanningTree (g). Every-
     *  thing else is private.
     */
    public static Graph minimumSpanningTree (Graph g) {
        return new Kruskal (g).minimumSpanningTree();
    }

    /*
     *  Private inner class to store edges. Implements Comparable
     *  because we need to sort by weight.
     */
    private static class Edge implements Comparable<Edge> {
        int x, y; /* Endpoints */
        double w; /* Weight    */

        private Edge (int x, int y, double w) {
            this.x = x;  this.y = y;  this.w = w;
        }

        /*
         *  compareTo says that this < that iff this.w < that.w.
         */
        @Override
        public int compareTo (Edge that) {
            if (this.w < that.w) return -1;
            else if (this.w == that.w) return 0;
            else return +1;
        }
    }

    private Graph g;                      /* The input graph.               */
    private final int n;                  /* Number of vertices in g.       */
    private List<Integer>[] componentOf;  /* Components of the tree so far. */

    private Kruskal (Graph g) {
        this.g = g;
        n = g.numVertices();

        /*
         *  Initially, the graph being built has no edges, so every
         *  vertex is in a component of its own.
         */
        componentOf = (List<Integer>[])new List[n];
        for (int i = 0; i < n; i++) {
            componentOf[i] = new ArrayList<Integer>();
            componentOf[i].add (i);
        }
    }

    /*
     *  Calculate a minimum spanning tree of the input graph, using
     *  Kruskal's algorithm.
     */
    private Graph minimumSpanningTree () {
        List<Edge> edges = getEdgeList (g);
        Collections.sort (edges); /* Increasing order by weight. */

        Graph mst = new MatrixGraph (n, Graph.UNDIRECTED_GRAPH);
        for (Edge e : edges) {
            if (componentOf[e.x] != componentOf[e.y]) {
                mergeComponents (e.x, e.y);
                mst.addEdge (e.x, e.y, e.w);
            }
        }
        return mst;
    }

    /*
     *  Get the list of all edges in a graph.
     */
    private static List<Edge> getEdgeList (Graph g) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < g.numVertices(); i++)
            for (int j : g.neighbours (i))
                if (j < i) /* Would add each edge twice, without this. */
                    edges.add (new Edge (i, j, g.weight (i, j)));
        return edges;
    }

    /*
     *  Merge the components containing vertices x and y in the graph
     *  that's being built. We ensure that y is in the smaller component.
     *  This is because, if the smaller component is added to the larger,
     *  it must at least double in size. Since doubling can only happen
     *  log n times before the whole graph is one component, this ensures
     *  that no vertex can be moved into a new component more than log n
     *  times.
     */
    private void mergeComponents (int x, int y) {
        /*
         *  If x is in the smaller component, swap the arguments.
         */
        if (componentOf[x].size() < componentOf[y].size())
            mergeComponents (y, x);

        /*
         *  Add all vertices from component y into component x, and update
         *  their component lists to be x's list.
         */
        componentOf[x].addAll(componentOf[y]);
        for (int i : componentOf[y])
            componentOf[i] = componentOf[x];
    }

    /*
     *  Test code to produce a minimum spanning tree of the map of Essex
     *  that was used in lecture 7. Compare the output with the final
     *  frame of slide 209.
     */
    public static void main (String[] args) {
        Graph t = minimumSpanningTree(GraphOfEssex.getGraph());

        List<Edge> edges = getEdgeList (t);
        for (Edge e : edges)
            System.out.println(GraphOfEssex.TOWN[e.x]
                                 + "-" + GraphOfEssex.TOWN[e.y]
                                 + ": " + e.w);
    }
}
