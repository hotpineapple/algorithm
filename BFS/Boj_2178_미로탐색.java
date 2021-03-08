import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2178_미로탐색 {

	static int N,M;
	static int[][] map;
	
	static class Graph{
	
		int gN;
		boolean[] isVisited;
		ArrayList<ArrayList<int[]>> adj;
		
		Graph(){}
		Graph(int n){
			gN=n;
			isVisited = new boolean[n];
			adj = new ArrayList<>();
			for(int i=0;i<n;i++) adj.add(new ArrayList<>());
		}
		
		void addEdge(int u, int[] v) {
			adj.get(u).add(v);  
		}
		
		int bfs() {
			
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {0,0});
			isVisited[0] = true;
			int level=1;
			
			while(!q.isEmpty()) {

				for(int i=0, size = q.size(); i<size; i++) {
					
					int[] curr = q.poll();
//					System.out.println("curr: "+ curr[0]+","+curr[1]);
					Iterator<int[]> it = adj.get(curr[0]*M+curr[1]).iterator();
					while(it.hasNext()) {
						
						int[] next = it.next();
						if(next[0]==N-1 && next[1]==M-1) return level+1;
						if(!isVisited[next[0]*M+next[1]]) {
							
							q.offer(next);
							isVisited[next[0]*M+next[1]]=true;
						}
					}
				}
				level++;
			}
			
			return -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		Graph g = new Graph(N*M);
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j-1)-'0';
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				
				int[] dr = {-1,1,0,0};
				int[] dc = {0,0,-1,1};
				
				for(int d = 0;d<4;d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(map[nr][nc]==1) g.addEdge((i-1)*M+(j-1), new int[] {nr-1,nc-1});
				}
			}
		}
		System.out.println(g.bfs());
	}

}
