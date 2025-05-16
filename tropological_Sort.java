package daa_problem;

import java.util.*;

public class tropological_Sort {
	static final String white = "white";
	static final String gray = "gray";
	static final String black = "black";

	static class Graph {
		int v;
		String[] color;
		List<List<Integer>> adj;
		boolean hasCycle = false;
		List<Integer> tO = new ArrayList<>(); 

		public Graph(int v) {
			this.v = v;
			adj = new ArrayList<>();
			for (int i = 0; i < v; i++) {
				adj.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v) {
			adj.get(u).add(v);
		}

		public void DFS() {
			color = new String[v];
			Arrays.fill(color, white);

			for (int u = 0; u < v; u++) {
				if (color[u].equals(white)) {
					DFSVisit(u);
				}
			}

			if (hasCycle) {
				System.out.println("The graph contains a cycle. Topological sort not possible.");
			} else {
				Collections.reverse(tO); 
				System.out.println("Topological Sort:");
				for (int node : tO) {
					System.out.print(node + " ");
				}
			    System.out.println();
			}
		}

		public void DFSVisit(int u) {
			color[u] = gray;

			for (int v : adj.get(u)) {
				if (color[v].equals(white)) {
					DFSVisit(v);
				} else if (color[v].equals(gray)) {
					hasCycle = true;
				}
			}

			color[u] = black;
			System.out.print(u + " ");
			tO.add(u); 
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(3, 1);
		g.addEdge(3, 2);
		g.addEdge(4, 3);
		g.addEdge(4, 5);
		g.addEdge(5, 6);

		g.DFS();
	}
}

