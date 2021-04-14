import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576_토마토2 {
	
	static class Node {
		int[] pos;
		Node next;
		public Node(int[] pos, Node next) {
			super();
			this.pos = pos;
			this.next = next;
		}
	}
	
	static int N, M, map[][];
	static Node[] adj; // 인접리스트
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		adj = new Node[(N+2)*M+2]; // N+1 * M + M+1
		
		Arrays.fill(map[0], -1);
		Arrays.fill(map[N+1], -1);
		for(int i=0;i<=N+1;i++) {
			map[i][0] = -1;
			map[i][M+1] = -1;
		}
				
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		List<int[]> list = new ArrayList<>(); //익은 토마토의 위치 
 		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] == 1) list.add(new int[] {i,j});
				
				for(int d=0;d<4;d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(map[nr][nc] != -1) { //빈칸이 아닌 토마토인 경우에만 인접리스트에 추가
						adj[i*M+j] = new Node(new int[] {nr,nc}, adj[i*M+j]);
//						adj[nr*M+nc] = new Node(new int[] {i,j}, adj[nr*M+nc]);
					}
				}
			}		
		}
 		
 		System.out.println(bfs(list));
		
	}
	private static int bfs(List<int[]> list) {
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N+2][M+2];
		Iterator<int[]> it = list.iterator();
		while(it.hasNext()) {
			int[] next = it.next();
			q.offer(next);
			isVisited[next[0]][next[1]] = true;
		}
		
		int len = 0;
		while(!q.isEmpty()) {
			
			int size = q.size();	
			while(--size>=0) {
				
				int[] curr = q.poll();

				for(Node temp = adj[curr[0]*M+curr[1]]; temp !=null; temp = temp.next) {
					if(!isVisited[temp.pos[0]][temp.pos[1]]) {
						q.offer(temp.pos);
						map[temp.pos[0]][temp.pos[1]] = 1;
						isVisited[temp.pos[0]][temp.pos[1]] = true;
					}
				}
			}
			len++;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i][j] == 0) return -1;
			}
		}
		
		return len-1;
	}
}
