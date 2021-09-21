import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,ans;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine()," ");
		
		int r = Integer.parseInt(st.nextToken()); //청소기 위치
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken()); //청소기방향 0,1,2,3-북,동,남,서
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(r,c,dir);
		System.out.println(ans);
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	private static void clean(int r, int c, int dir) {
		if(map[r][c]==0)ans++;
		map[r][c] = 2;
	
		search(r,c,dir);
		
	}
	private static void search(int r, int c, int dir) {
		boolean move = false;
		for (int i = 0; i < 4; i++) {
			dir = dir==0? 3 : dir-1;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(map[nr][nc] != 0) {//벽이거나 이미 청소한 칸이면
				continue; 
			}
			move = true;
			clean(nr,nc,dir);
			break;
		}
		
		if(move) return;
        
		// 인접 4방향 모두 벽이거나 이미 청소한 칸이면 후진 시도
		int d = -1;
		
		if(dir==0)d = 2;
		else if(dir==1)d = 3;
		else if(dir==2)d = 0;
		else if(dir==3)d = 1;
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		if(map[nr][nc] == 1) {// 후진도 불가능하면  종료		
			return;
		}else if(map[nr][nc] == 2) {
			search(nr,nc,dir);
		}
		
	}
}
