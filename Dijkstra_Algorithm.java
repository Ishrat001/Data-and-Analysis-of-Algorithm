package daa_problem;

import java.util.*;

public class Dijkstra_Algorithm {
	static List<List<int[]>> adj;
	static int[] d;
	static boolean[] S;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		d = new int[V+1];
		S = new boolean[V+1];		
		adj = new ArrayList<>();

		for(int i=0; i<=V; i++) {
			adj.add(new ArrayList<>());
		}

		Arrays.fill(d, Integer.MAX_VALUE);
		Arrays.fill(S, false);

		for(int j=1; j<=E; j++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			addEdge(u, v, w);
		}

		int source = sc.nextInt();
		d[source] = 0;

		//creating a priority queue(a) which only has two elements
		//a[0] = distance/weight, a[1] = vertex
		pq = new PriorityQueue<>(Comparator.comparingInt(a-> a[0]));
		//Compare arrays based on their first element (a[0] = distance)
		//the queue always keeps the node with the smallest distance at the top

		pq.add(new int[] {0, source});
		//0 is the distance from the source to itself (always 0)


		while(!pq.isEmpty()) {
			int[] current = pq.poll();
			int du = current[0];
			int u = current[1];

			if(S[u]) continue;
			S[u] = true;

			for(int[] edge: adj.get(u)) {
				int v = edge[0];
				int weight = edge[1];

				if(d[v] > d[u] + weight) {
					d[v] = d[u] + weight;
					pq.add(new int[] {d[v], v});
				}
			}
		}

		for(int i=1; i<=V; i++) {
			System.out.println(source + "->" + i + " " + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]));
		}


	}

	static void addEdge(int u, int v, int w) {
		adj.get(u).add(new int[] {v, w});
	}

}
