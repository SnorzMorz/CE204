package labs.lab7;

import graphs.*;

import java.util.*;

/***********************************************************************
 *  Lab 7, exercise 2                                                  *
 **********************************************************************/

public class FloydWarshall {
    /*
     *  To use the class, call Kruskal.minimumSpanningTree (g). Every-
     *  thing else is private.
     */
    public static double[][] allPairsShortestPaths (Graph g) {
        return new FloydWarshall(g).getDist();
    }

    private Graph g;       /* The input graph.                        */
    private final int n;   /* Number of vertices in g.                */
    double[][] dist;       /* Matrix of distances.                    */

    private FloydWarshall (Graph g) {
        this.g = g;
        n = g.numVertices();
        dist = new double[n][n];
    }

    /*
     *  The main algorithm.
     *
     *  Strictly speaking, this isn't quite as described in the lab
     *  sheet. To implement the lab sheet literally, we would use two
     *  arrays and the code would be
     *
     *  for (int k = 0; k < n; k++) {
     *      double newDist[][] = new double[n][n];
     *      for (int x = 0; x < n; x++)
     *          for (int y = 0; y < n; y++)
     *              newDist[x][y] = Math.min (dist[x][y], dist[x][k] + dist[k][y]);
     *      dist = newDist;
     *  }
     *
     *  The above code calculates dist(x,y,k+1) purely in terms of
     *  values dist(a,b,k). The code below calculates dist(x,y,k+1)
     *  using some values of the form dist(a,b,k) and some of the form
     *  dist(a,b,k+1). This is safe because we always have
     *  dist(a,b,n) <= dist(a,b,k+1) <= dist(a,b,k) so using
     *  dist(a,b,k+1) instead of dist(a,b,k) can only make the algorithm
     *  find the true answer faster; it can't make it find the wrong
     *  answer.
     */
    private double[][] getDist () {
        initializeDist();

        for (int k = 0; k < n; k++)
            for (int x = 0; x < n; x++)
                for (int y = 0; y < n; y++)
                    dist[x][y] = Math.min (dist[x][y], dist[x][k] + dist[k][y]);
        return dist;
    }

    /*
     *  Initialize the distance array so that
     *                   (  0         if x==y
     *      dist[x][y] = <  w         if (x,y,w) is an edge in g
     *                   (  infinity  otherwise
     */
    private void initializeDist () {
        for (int x = 0; x < n; x++) {
            Arrays.fill (dist[x], Double.POSITIVE_INFINITY);
            for (int y : g.neighbours(x))
                dist[x][y] = g.weight (x,y);
            dist[x][x] = 0;
        }
    }

    /*
     *  Test code.
     */
    public static void main (String[] args) {
        Graph g = new MatrixGraph(4, Graph.UNDIRECTED_GRAPH);

        g.addEdge (0,1,1);
        g.addEdge (1, 2, 6);
        g.addEdge (2,3,2);
        g.addEdge (3,1, 3);

        double[][] dist = allPairsShortestPaths(g);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.printf("%1.0f ", dist[i][j]);
            System.out.println();
        }
    }
}
