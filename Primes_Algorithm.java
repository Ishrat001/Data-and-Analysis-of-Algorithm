package daa_problem;

import java.util.*;

public class Primes_Algorithm {
	static List<List<int[]>> adj;
	static int[] key, p;
	static boolean[] inMST;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		adj = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			adj.add(new ArrayList<>());
		}

		key = new int[V+1];
		p = new int[V+1];
		inMST = new boolean[V+1];

		for(int i=0; i<E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();

			addEdge(u, v, w);
		}

		int start = sc.nextInt();
		key[start] = 0;

		pq = new PriorityQueue<>(Comparator.comparingInt(a-> a[0]));
		pq.add(new int[] {0, start});

		while(!pq.isEmpty()) {
			int[] current = pq.poll();
			int u = current[1];

			if(inMST[u]) continue;
			inMST[u] = true;

			for(int[] edge: adj.get(u)) {
				int v = edge[0];
				int weight = edge[1]; 

				if(!inMST[v] && weight<key[v]) {
					key[v] = weight;
					p[v] = u;
					pq.add(new int[] {key[v], v});
				}
			}
		}

		for(int i=1; i<=V; i++) {
			if(p[i]!=-1) {
				System.out.println(p[i] + " " + i + key[i]);
			}
		}     
	}

	static void addEdge(int u, int v, int w) {
		adj.get(u).add(new int[] {v, w});
	}
}
