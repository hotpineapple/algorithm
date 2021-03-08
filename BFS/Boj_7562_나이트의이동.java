import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7562_나이트의이동 {

	static int[][] map;
	
	static class Graph{
		
		int N;
		boolean[] isVisited;
		ArrayList<ArrayList<int[]>> adj;
		
		public Graph() {}
		public Graph(int n) {
			N=n;
			adj = new ArrayList<>();
			for(int i=0;i<n;i++) adj.add(new ArrayList<>());
			isVisited = new boolean[n];
		}

		void addEdge(int u, int[] v) {
			adj.get(u).add(v);
		}
		
		int bfs(int[] s, int[] e) {
			
			if(s[0]==e[0] && s[1]==e[1]) return 0;
			
			Queue<int[]> q = new LinkedList<>();
			q.offer(s);
			isVisited[(int) (s[0]*Math.sqrt(N)+e[1])] = true;
			
			int level=0;
			while(!q.isEmpty()) {
				
				int size = q.size();
				while(--size>=0) {
					
					int[] curr = q.poll();
					Iterator<int[]> it = adj.get((int) (curr[0]*Math.sqrt(N)+curr[1])).iterator();
					while(it.hasNext()) {
						int[] next = it.next();
						if(next[0]==e[0] && next[1]==e[1]) return level+1;

						if(!isVisited[(int) (next[0]*Math.sqrt(N)+next[1])]) {
							q.offer(next);
							isVisited[(int) (next[0]*Math.sqrt(N)+next[1])] = true;
						}
					}
				}
				level++;
			}
			
			return -1;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<TC; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			Graph g = new Graph(N*N);
			st = new StringTokenizer(br.readLine(), " ");
			int[] start = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			st = new StringTokenizer(br.readLine(), " ");
			int[] end = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			//간선 정보 입력
			int[] dr = {-1,-2,-2,-1,1,2,2,1};
			int[] dc = {-2,-1,1,2,2,1,-1,-2};
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for(int d=0;d<8;d++) {
						
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr>=0 && nr<N && nc>=0 && nc<N) {
							g.addEdge(i*N+j, new int[] {nr,nc});
						}
					}
					
				}
			}
			//bfs수행
			sb.append(g.bfs(start,end)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
