package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {

	static class Point {
		int r, c, breakCnt;

		public Point(int r, int c, int breakCnt) {
			super();
			this.r = r;
			this.c = c;
			this.breakCnt = breakCnt;
		}		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = line[j]-'0';
			}
		}
		
		boolean[][][] vst = new boolean[2][N][M];
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0,0,0));
		vst[0][0][0] = true;
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		int level = 0;
		boolean flag = false;
		OUTER:while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				Point curr = q.poll();
				int r = curr.r;
				int c = curr.c;
				int bc = curr.breakCnt;
				
				if(r==N-1 && c==M-1) {
					flag = true;
					break OUTER;
				}
				
				for (int d = 0; d < 4; d++) {
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nr==N ||nc<0 || nc == M) continue;
					
					if(map[nr][nc] == 0) { // 벽 아닌 경우
						
						if(!vst[bc][nr][nc]) {
							q.offer(new Point(nr,nc,bc));
							vst[bc][nr][nc] = true;
						}
						
					}else { // 벽인 경우
						
						if(bc != 0) continue; // 이미 벽 한번 부순 상태면 안됨
						
						if(!vst[bc+1][nr][nc]) {
							
							q.offer(new Point(nr,nc,bc+1));
							vst[bc+1][nr][nc] = true;
						}
					}
					
					
				}
			}
			level++;
		}
		
		System.out.println(flag?level:-1);
	}

}
