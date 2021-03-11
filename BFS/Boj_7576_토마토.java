import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576_토마토 {
	
	static int N,M;
	static int[][] map;
	
	static class Graph{
		
		ArrayList<ArrayList<int[]>> adj;
		boolean[][] isVisited;
		
		Graph(){
			adj = new ArrayList<>();
			for(int i=0;i<=(N+1)*(M+1);i++) adj.add(new ArrayList<>());
			isVisited = new boolean[N+1][M+1];
		}
		
		void addEdge(int[] u, int[] v) {
			adj.get(u[0]*M+u[1]).add(v);
		}
		
		int bfs(ArrayList<int[]> list) {
			
			Queue<int[]> q = new ArrayDeque<>();
			Iterator<int[]> it1 = list.iterator();
			while(it1.hasNext()) {
				int[] next = it1.next();
				q.offer(next);
				isVisited[next[0]][next[1]] = true;
			}
			
			int len = 0;
			while(!q.isEmpty()) {
				
				int size = q.size();	
				while(--size>=0) {
					
					int[] curr = q.poll();
					Iterator<int[]> it2 = adj.get(curr[0]*M+curr[1]).iterator();
					
					while(it2.hasNext()) {
						int[] next = it2.next();
						if(!isVisited[next[0]][next[1]]) {
							q.offer(next);
							map[next[0]][next[1]] = 1;
							isVisited[next[0]][next[1]] = true;
						}
					}
				}
				len++;
			}
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(map[i][j]==0) return -1;
				}
			}
			return len-1;
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		for(int i=0;i<=N+1;i++) Arrays.fill(map[i], -1);
				
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Graph g = new Graph();
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		ArrayList<int[]> list = new ArrayList<>();
 		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j]==1) list.add(new int[] {i,j});
				for(int d=0;d<4;d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(map[nr][nc]!=-1) g.addEdge(new int[] {i,j}, new int[] {nr,nc});
				}
			}		
		}
 		System.out.println(g.bfs(list));	
	}
    
}
