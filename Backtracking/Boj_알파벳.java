import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1987_alphabet {

	static int R,C,ans;
	static char[][] map;
	static boolean[] vst;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		vst = new boolean[26];//A~Z 방문여부 체크
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = str[j];
			}
		}
		
		dfs(0,0,1);
		System.out.println(ans);
	}
	private static void dfs(int r, int c, int cnt) {
		if(vst[map[r][c]-'A']) { // 이미 지나온 알파벳이라면 최대값 체크 후 탐색종료
			ans = Math.max(ans, cnt-1);
			return;
		}
		
		vst[map[r][c]-'A'] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0||nc<0||nr==R||nc==C) continue;
			
			dfs(nr,nc,cnt+1);
		}
		vst[map[r][c]-'A'] = false;
		
	}

}
