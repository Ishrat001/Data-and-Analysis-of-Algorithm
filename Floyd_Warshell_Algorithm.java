package daa_problem;

import java.util.*;

public class Floyd_Warshell_Algorithm {

	static int[][] adj ;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();

		adj = new int[V][V];
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				if(i==j) {
					adj[i][j] = 0;
				}else {
					adj[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for(int i=0; i<E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();

			adj[u][v] = w;
		}

		for(int k=0; k<V; k++) {
			for(int i=0; i<V; i++) {
				for(int j=0; j<V; j++) {
					if (adj[i][k] != Integer.MAX_VALUE && adj[k][j] != Integer.MAX_VALUE &&
							adj[i][k] + adj[k][j] < adj[i][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}

		for (int i = 0; i <V; i++) {
			for (int j = 0; j <V; j++) {
				if(adj[i][j]==Integer.MAX_VALUE) {
					System.out.print("No Path");
				}else {
					System.out.print(adj[i][j] + " ");
				}
			}
			System.out.println();
		}

	}

}
