package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {

	static class Point {
		int r,c;
		int key; //0~63
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		public Point(int r, int c,int key) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
		}
	
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 가로 길이(행)
		int M = Integer.parseInt(st.nextToken()); // 세로 길이 (열)
		
		char[][] map = new char[N][M];
		Point start = null;
		ArrayList<Point> end = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = line[j];
				if(map[i][j]=='0') start = new Point(i,j);
				else if(map[i][j]=='1') end.add(new Point(i,j));

			}
		}
		
		// BFS
		Queue<Point> q = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[64][N][M];
		q.offer(start);
		isVisited[0][start.r][start.c] = true;
		map[start.r][start.c] = '.';
		
		int[] dr = {-1,1,0,0}; // 상하좌우
		int[] dc = {0,0,-1,1};
		boolean flag = false;
		int level = 0;

		OUTER: while(!q.isEmpty()) {
			
			int size = q.size();
			while(--size>=0) {
				
				Point curr = q.poll();
				int currKey = curr.key;
				System.out.println(curr.r +" "+curr.c+" "+curr.key);
				
				for (int i=0;i<end.size();i++) {
					Point e = end.get(i);
					
					if(curr.r == e.r && curr.c == e.c) { // 도착점에 도달하면 끝
						flag = true;
						break OUTER;
					}
				}
				
				for (int d = 0; d < 4; d++) { // 상하좌우 탐색
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					
					if(nr>=0 && nr < N && nc>=0 && nc < M) continue;
						
					if(map[nr][nc] == '#') continue; 
					
					if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F' 
							&& (currKey & (1 << (map[nr][nc]-'A'))) == 0) continue;
			
					if(map[nr][nc] >= 'a' && map[nr][nc] <= 'f') currKey |= (1 << (map[nr][nc]-'a'));
		
					if(isVisited[currKey][nr][nc]) continue;	
					
					q.offer(new Point(nr,nc,currKey));
					isVisited[currKey][nr][nc] = true;		
						
					
				}
			} // inner while end
			
			level++;
		} // outer while end
		
		//출력
		if(flag) System.out.println(level);
		else System.out.println(-1);
	}

}
