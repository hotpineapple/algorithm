package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길 {

	static int M, N, map[][], D[][]; 
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken()); // 행
		N = Integer.parseInt(st.nextToken()); // 열
		map = new int[M][N]; 
		D = new int[M][N]; //dp
		for (int i = 0; i < M; i++) Arrays.fill(D[i], -1);
		
		for (int i = 0; i < M; i++) { 
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) { 
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(new int[] {0,0})); // 최종 결과 출력
	}

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	private static int dfs(int[] curr) {
		int r = curr[0];
		int c = curr[1];
		System.out.println(r+" "+c);

		if(r == M-1 && c == N-1) { //오른쪽 아래 도착
			System.out.println("도착");
			return 1;
		}
		
		if(D[r][c] == -1) D[r][c] = 0; //방문처리
	
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr == M || nc < 0 || nc == N) continue;
			
			if(map[r][c] <= map[nr][nc]) continue;
			
			if(D[nr][nc] == -1) D[r][c] += dfs(new int[] {nr,nc}); // nr,nc를 방문하지 않은 경우에만 재귀 호출
			else D[r][c] += D[nr][nc]; // nr,nc룰 이미 방문한 경우에는 그곳에서 목적지까지 갈 수 있는 방법의 수를 현재위치 (r,c)에서 목적지까지 가는 방법의 수에 더해줌.
			
      // 디버깅 위한 출력
			System.out.println(r+" "+c);
			for (int i = 0; i <M; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%3d ",D[i][j]);
				}
				System.out.println();
			}
			System.out.println("===================");
		}
		
		return D[r][c];
		
	}
	
}

