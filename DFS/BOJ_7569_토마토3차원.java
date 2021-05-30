import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토3차원 {

	static int N, M, H;
	static boolean[][][] vst;
	static int [][][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		vst = new boolean[H][N][M];
		map = new int[H][N][M];
		
		Queue<int[]> q = new ArrayDeque<>();
		int cnt = 0; //익어야 할 토마토의 개수
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 1) {
						vst[i][j][k] = true;
						q.offer(new int[] {i,j,k});
					} else if(map[i][j][k] == 0) cnt++;
				}
			}
		}

		int[] dr = {1,-1,0,0,0,0};
		int[] dc = {0,0,1,-1,0,0};
		int[] dh = {0,0,0,0,1,-1};
		
		int ans = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] curr = q.poll();
				int h = curr[0];
				int r = curr[1];
				int c = curr[2];
						
				for (int d = 0; d < 6; d++) {
					int nh = h + dh[d];
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nh < 0 || nh == H ||nr < 0 || nr == N ||nc < 0 || nc == M) continue; // 범위 체크
					
					if(!vst[nh][nr][nc] && map[nh][nr][nc] == 0) { // 방문체크 & 빈칸체크
						vst[nh][nr][nc] = true;
						q.offer(new int[] {nh,nr,nc});
						cnt--;
					}
					
				}
			}
			
			ans++;
		}
		
		System.out.println(cnt == 0 ? ans-1 : -1); //안 익은 토마토가 남아있다면 -1 
	}

}
