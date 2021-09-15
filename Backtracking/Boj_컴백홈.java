import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_comebackhome {

	static int R,C,K,ans;
	static char[][] map;
	static boolean[][] vst;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		vst = new boolean[R][C];//A~Z 방문여부 체크
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = str[j];
			}
		}
		
		dfs(R-1,0,0);
		System.out.println(ans);
	}
	private static void dfs(int r, int c, int cnt) {

		if(r==0 && c==C-1) { // 집에 도착하면 거리 체크 후 종료
			if(cnt == K-1) ans++;
			return;
		}
		
		vst[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0||nc<0||nr==R||nc==C||map[nr][nc]=='T') continue;
			if(vst[nr][nc]) continue;
			
			dfs(nr,nc,cnt+1);
		}
		vst[r][c] = false;
		
	}

}
