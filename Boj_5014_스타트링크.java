import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_5014_스타트링크 {
	
	static int F,S,G,U,D;
	
	static class Graph{
		
		int N;
		boolean[] isVisited;
		ArrayList<ArrayList<Integer>> adj;
		
		public Graph() {}
		public Graph(int n) {
			N=n;
			adj = new ArrayList<>();
			for(int i=0;i<n;i++) adj.add(new ArrayList<>());
			isVisited = new boolean[n];
		}

		void addEdge(int u, int v) {
			adj.get(u).add(v);
		}
		
		String bfs() {
			
			if(S==G) return "0";
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(S);
			isVisited[S] = true;
			
			int level=0;
			while(!q.isEmpty()) {
				
				int size = q.size();
				while(--size>=0) {
					
					int curr = q.poll();
					Iterator<Integer> it = adj.get(curr).iterator();
					while(it.hasNext()) {
						int next = it.next();
						if(next==G) return level+1+"";

						if(!isVisited[next]) {
							q.offer(next);
							isVisited[next] = true;
						}
					}
				}
				level++;
			}
			
			return "use the stairs";
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		Graph g = new Graph(F+1);
		for (int i = 1; i <= F; i++) {
			
			if(i+U <= F) g.addEdge(i, i+U);
			if(i-D > 0) g.addEdge(i, i-D);
		}
		
		System.out.println(g.bfs());
	}

}
