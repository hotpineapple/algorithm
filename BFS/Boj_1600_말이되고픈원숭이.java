import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 방문 처리 차원 +1 한 bfs
 * */
public class Boj_1600_말이되고픈원숭이 {
	
	static class Point {
		int x,y;
		int moveLikeHorse;
		
		public Point(int x, int y, int moveLikeHorse) {
			super();
			this.x = x;
			this.y = y;
			this.moveLikeHorse = moveLikeHorse;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine()); // 말처럼 이동할 수 있는 횟수
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		boolean[][][] vst = new boolean[K+1][H][W];
		int[][] map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] drHorse = {-1,-2,-2,-1,1,2,2,1}; //우상향부터 시계방향
		int[] dcHorse = {-2,-1,1,2,2,1,-1,-2};
		int[] drMongkey = {-1,1,0,0};
		int[] dcMongkey = {0,0,-1,1};
		
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0,0,0));
		
		int ans=0;
		boolean flag = false;
		OUTER:while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				Point curr = q.poll();
				int r = curr.x;
				int c = curr.y;
				int cnt = curr.moveLikeHorse;
				
				if(r == H-1 && c == W-1) {
					flag = true;
					break OUTER; // 오른쪽 아래 도착하면 끝
				}
				
				
				// 말처럼 이동
				for (int d = 0; d < 8; d++) {
					
					if(cnt == K) break;
					
					int nr = r + drHorse[d];
					int nc = c + dcHorse[d];
					
					if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue; // 범위 체크
					if(map[nr][nc] == 1) continue; // 평지체크
					if(vst[cnt+1][nr][nc]) continue; // 방문체크
					
					q.offer(new Point(nr,nc,cnt+1));
					vst[cnt+1][nr][nc] = true;
					
				}
				
				//원숭이처럼 이동
				for (int d = 0; d < 4; d++) {
					
					int nr = r + drMongkey[d];
					int nc = c + dcMongkey[d];
					
					if(nr < 0 || nr == H || nc < 0 || nc == W) continue; // 범위 체크
					
					if(vst[cnt][nr][nc]) continue; // 방문체크
					if(map[nr][nc] == 1) continue; // 평지체크
					q.offer(new Point(nr,nc,cnt));
					vst[cnt][nr][nc] = true;
				}
			}
      
			ans++;
		}
		
		System.out.println(flag?ans:-1);
	}

}
