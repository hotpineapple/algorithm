import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[][] map;
	static boolean[][] vst;
	static List<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int ans=0;
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break; // 종료조건
			
			map = new int[N][M];
			vst = new boolean[N][M];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if(num==1) {
						list.add(new int[] {i,j}); //dfs 시작할 지점 저장
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				int[] curr = list.get(i);
				int r = curr[0];
				int c = curr[1];

			}
			
			
			for (int i = 0; i < list.size(); i++) {
				int[] curr = list.get(i);
				int r = curr[0];
				int c = curr[1];
				if(!vst[r][c]) {
					ans++;
					dfs(r,c);
				}
			}
			System.out.println(ans);
		}
	}
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	private static void dfs(int r, int c) {
		
		vst[r][c] = true;
		
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0||nc<0||nr==N||nc==M||map[nr][nc]==0) continue;
			
			if(!vst[nr][nc]) dfs(nr,nc);
      
		}
	}
}
