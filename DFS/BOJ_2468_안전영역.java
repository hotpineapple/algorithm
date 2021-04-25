import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	
	static int N, ans, H, max = 0, map[][];
	static boolean[][] vst;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 100; i++) go(i);
		
		System.out.println(max);
	}
	
	private static void go(int h){
	  vst = new boolean[N][N];
		H = h;
		ans = 0;
    
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > h && !vst[i][j]) {
					ans++;
					dfs(new int[] {i,j});
				}
			}
		}
		
		max = Math.max(max, ans);
	}
	
	private static void dfs(int[] pos) {
		
		vst[pos[0]][pos[1]] = true;
		
		for(int d = 0; d < 4; d++) {
			int nr = pos[0] + dr[d];
			int nc = pos[1] + dc[d];
			
			if(nr < 0 || nr == N || nc < 0 || nc == N || vst[nr][nc]) continue;
			
			if(map[nr][nc] > H) dfs(new int[] {nr,nc});
		}
		
	}
}
