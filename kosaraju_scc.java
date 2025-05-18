package daa_problem;

import java.util.*;

public class kosaraju_scc {
	/*static final String white = "WHITE";
	static final String gray = "GRAY";
	static final String black = "BLACK";

	static class Graph{
		int v;
		List<List<Integer>> adj;
		String [] color;
		int [] d, f, prev;
		int time;

		public Graph(int v) {
			this.v = v;
			adj = new ArrayList<>();
			for(int i =0; i<v; i++) {
				adj.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v) {
			adj.get(u).add(v);
		}

		public void DFS(List<Integer> order) {
			color = new String[v];
			d = new int[v];
			f = new int[v];
			prev = new int[v];
			Arrays.fill(color, white);
			Arrays.fill(d, Integer.MAX_VALUE);
			Arrays.fill(f, Integer.MAX_VALUE);
			Arrays.fill(prev, -1);
			time = 0;

			for(int u = 0; u<v; u++) {
				if(color[u].equals(white)) {
					DFSVisit(u, order);
				}
			}
		}

		public void DFSVisit(int u, List<Integer> order) {
			color[u] = gray;
			time = time + 1;
			d[u] = time;

			for(int v : adj.get(u)) {
				if(color[v].equals(white)) {
					prev[v] = u;
					DFSVisit(v, order);
				}
			}

			color[u] = black;
			time = time + 1;
			f[u] = time;
			order.add(u);
		}

		public Graph getTranspose() {
			Graph gt = new Graph(v);
			for(int u = 0; u < v; u++) {
				for(int v : adj.get(u)) {
					gt.addEdge(v, u);
				}
			}
			return gt;
		}

		public void printSCC() {
			List<Integer> order = new ArrayList<>();
			DFS(order);

			Graph gt = getTranspose();
			Collections.reverse(order);

			gt.color = new String[v];
			Arrays.fill(gt.color, white);

			for(int u : order) {
				if(gt.color[u].equals(white)) {
					List<Integer> component = new ArrayList<>();
					gt.DFSCollect(u, component);
					System.out.println(component);
				}
			}
		}

		private void DFSCollect(int u, List<Integer> component) {
			color[u] = gray;
			component.add(u);
			for(int v : adj.get(u)) {
				if(color[v].equals(white)) {
					DFSCollect(v, component);
				}
			}
			color[u] = black;
		}

	}*/
	
	
	    List<Integer>[] graph;
	    List<Integer>[] reversGraph;
	    boolean[] visited;
	    Stack<Integer> stack;
	    int V, E;

	    kosaraju_scc(int V, int E){
	        this.V = V;
	        this.E = E;
	        graph = new ArrayList[V];
	        reversGraph = new ArrayList[V];
	        visited = new boolean[V];
	        stack = new Stack<>();

	        for(int i=0; i<V; i++) {
	            graph[i] = new ArrayList<>();
	            reversGraph[i] = new ArrayList<>();
	        }
	    }

	    void adddEdge(int u, int v) {
	        graph[u].add(v);
	        reversGraph[v].add(u);
	    }

	    void DFS(int V) {
	        Arrays.fill(visited, false);
	        for(int i = 0; i < V; i++) {
	            if(!visited[i]) {
	                DFSVisit(i);
	            }
	        }
	    }

	    void DFSVisit(int u) {
	        visited[u] = true;
	        for(int to : graph[u]) {
	            if(!visited[to]) {
	                DFSVisit(to);
	            }
	        }
	        stack.push(u);
	    }

	    void DFSUtilReverse(int u, List<Integer> component) {
	        visited[u] = true;
	        component.add(u);
	        for(int to : reversGraph[u]) {
	            if(!visited[to]) {
	                DFSUtilReverse(to, component);
	            }
	        }
	    }

	    void findSCCs() {
	        // Step 1: Fill stack with finish times
	        DFS(V);

	        // Step 2: Reset visited and process nodes in reverse finishing time
	        Arrays.fill(visited, false);
	        while(!stack.isEmpty()) {
	            int node = stack.pop();
	            if(!visited[node]) {
	                List<Integer> component = new ArrayList<>();
	                DFSUtilReverse(node, component);
	                System.out.println("SCC: " + component);
	            }
	        }
	    }

	    public static void main(String[] args) {
	        int V = 5, E = 5;
	        kosaraju_scc g = new kosaraju_scc(V, E);

	        g.adddEdge(0, 2);
	        g.adddEdge(2, 1);
	        g.adddEdge(1, 0);
	        g.adddEdge(0, 3);
	        g.adddEdge(3, 4);

	        g.findSCCs();
	  }
}
