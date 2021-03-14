import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_10026_적록색약 {

	static int N;
	static boolean[][] isVisited;
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N+2][N+2];
		map = new char[N+2][N+2];
		for (int i = 1; i <= N; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 1; j <= N; j++) {
				map[i][j] = str[j-1];
			}
		}

		int ans1=0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(!isVisited[i][j]) {
					ans1++;
					dfs(i,j);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j]=='R') map[i][j]='G';
			}
		}
		for(int i = 1; i <= N; i++) Arrays.fill(isVisited[i], false);
		
		int ans2=0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(!isVisited[i][j]) {
					ans2++;
					dfs(i,j);
				}
			}
		}
		System.out.println(ans1+" "+ans2);

	}
	private static void dfs(int r, int c) {

		isVisited[r][c] = true;
		int[] dr = {-1,1,0,0};//상하좌우
		int[] dc = {0,0,-1,1};//상하좌우
		
		for(int d=0;d<4;d++) {
			int nr = r+dr[d]; 
			int nc = c+dc[d];
			
			if(!isVisited[nr][nc] && map[r][c] == map[nr][nc]) dfs(nr,nc);
		}

	}

}
