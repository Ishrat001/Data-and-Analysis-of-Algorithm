package daa_problem;

import java.util.*;

public class Articulation_point {
	int time = 0;
	List<List<Integer>> adj;
	int[] d, lowd, parent;
	boolean[] visited, isAP;
	List<String> bridges = new ArrayList<>();
	
	Articulation_point(int v){
		adj = new ArrayList<>();
		for(int i=0; i<=v; i++) {
			adj.add(new ArrayList<>());
		}
		d = new int[v+1];
		lowd = new int[v+1];
		parent = new int[v+1];
		visited = new boolean[v+1];
		isAP = new boolean[v+1];
		
		Arrays.fill(parent, -1);
	}
	
	void addEdge(int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}
	
	
	void FindAP(int v) {
		
		for(int u=1; u<=v; u++) {
			if(!visited[u]) {
				DFS(u);
			}
		}
		
		/*for(int i=0; i<v; i++) {
			if(isAP[i]) {
				System.out.println(i);
			}
		}*/
		
		for (String b : bridges) {
            System.out.println(b);
        }
	}
	
	void DFS(int u) {
		visited[u] = true;
		d[u] = lowd[u] = time++;
		
		for(int t: adj.get(u)) {
			if(!visited[t]) {
				parent[t] = u;
				DFS(t);
				
				if (lowd[t] > d[u]) {
                    bridges.add("(" + u + ", " + t + ")");
				}
				lowd[u] = Math.min(lowd[u], lowd[t]);
			}else if(t != parent[u]) {
				lowd[u] = Math.min(lowd[u], d[t]);
			}
		}
		 time++;
	}
	
	/*void DFS(int u) {
		visited[u] = true;
		d[u] = lowd[u] = time++;
		int children = 0;
		
		for(int t : adj.get(u)) {
			if(!visited[t]) {
				parent[t] = u;
				children++;
				DFS(t);
				
				lowd[u] = Math.min(lowd[u], lowd[t]);
				
				if(parent[u] == -1 && children > 1) {
					isAP[u] = true;
				}
				
				if(parent[u] != -1 && lowd[t] >= d[u]) {
					isAP[u] = true;
				}
			}else if(t != parent[u]) {
				lowd[u] = Math.min(lowd[u], d[t]);
			}
		}
	}*/
	
	public static void main(String[] args) {
		Articulation_point ap = new Articulation_point(7);
		//ap.addEdge(0, 1);
		ap.addEdge(1, 3);
		ap.addEdge(1, 2);
		ap.addEdge(4, 3);
		ap.addEdge(2, 3);
		ap.addEdge(4, 5);
		ap.addEdge(7, 6);
		ap.addEdge(5, 6);
		ap.addEdge(5, 7);
		
		ap.FindAP(7);
	}

}
