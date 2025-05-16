package daa_problem;

import java.util.*;

public class Kruskals_Algorithm {

	static int[] p, r;
	static List<int[]> edges = new ArrayList<>();
	static List<int[]> mst = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		p = new int[V + 1];
		r = new int[V + 1];

		
		for (int i = 1; i <= V; i++) {
			p[i] = i;
			r[i] = 0;
		}

		for (int i = 0; i < E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			edges.add(new int[] {w, u, v});
		}

		Collections.sort(edges, Comparator.comparingInt(a -> a[0]));

		for (int[] edge : edges) {
			int w = edge[0];
			int u = edge[1];
			int v = edge[2];

			if (find(u) != find(v)) {
				mst.add(new int[] {u, v, w});
				union(u, v);
			}
		}

		for (int[] i : mst) {
			System.out.println(i[0] + " - " + i[1] + i[2]);
		}
	}

	static int find(int u) {
		if (p[u] != u) {
			p[u] = find(p[u]);
		}
		return p[u];
	}
	static void union(int u, int v) {
		int pou = find(u);
		int pov = find(v);
		if (pou == pov) return;

		if (r[pou] < r[pov]) {
			p[pou] = pov;
		} else if (r[pou] > r[pov]) {
			p[pov] = pou;
		} else {
			p[pov] = pou;
			r[pou]++;
		}
	}
}
