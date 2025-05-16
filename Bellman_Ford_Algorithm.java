package daa_problem;

import java.util.*;

public class Bellman_Ford_Algorithm {
    static List<int[]> adj;
    static int[] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        adj = new ArrayList<>();
        d = new int[V + 1];

        Arrays.fill(d, Integer.MAX_VALUE);

        for (int i = 1; i <= E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            addEdge(u, v, w);
        }

        int source = sc.nextInt();
        d[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (int[] edge : adj) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (d[u] != Integer.MAX_VALUE && d[v] > d[u] + weight) {
                    d[v] = d[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        boolean hasNegativeCycle = false;
        for (int[] edge : adj) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (d[u] != Integer.MAX_VALUE && d[v] > d[u] + weight) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("Negative Weight Cycle Detected!");
        } else {
            for (int i = 1; i <= V; i++) {
                System.out.println(source + " -> " + i + " : " + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]));
            }
        }
    }

    static void addEdge(int u, int v, int w) {
        adj.add(new int[]{u, v, w});
    }
}
