import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1012_유기농배추 {

	static int N,M,K,cnt;
	static int[][] map;
	static boolean[][] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc=0; tc<TC; tc++) {
			
			st = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[M][N];
			isVisited = new boolean[M][N];
			
			for(int i=0; i<K; i++) {			
				st = new StringTokenizer(br.readLine()," ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			dfsAll();
			sb.append(cnt).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	private static void dfsAll() {
		
		cnt=0;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 && !isVisited[i][j]) {
					cnt++;
					dfs(new int[] {i,j});
				}
			}
		}
	}
	
	private static void dfs(int[] curr) {
//		System.out.println("curr: "+curr[0]+", "+curr[1]);
		isVisited[curr[0]][curr[1]] = true;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for(int d=0;d<4;d++) {
			int nr = curr[0] + dr[d];
			int nc = curr[1] + dc[d];
			if(nr>=0 && nr<M && nc>=0 && nc<N && map[nr][nc]==1 && !isVisited[nr][nc]) dfs(new int[] {nr,nc});
		}	
	}
	
}
