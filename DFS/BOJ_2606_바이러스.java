import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {

	static int N, ans;
	static boolean[][] adj;
	static boolean[] vst;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		// 방문처리
		vst = new boolean[N+1];
		
		//  N=100이하이므로 인접행렬 이용
		adj = new boolean[N+1][N+1]; 
		
		
		// 인접행렬 구성
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = true;
			adj[b][a] = true;
		}

		dfs(1);
		
		System.out.println(ans-1);
		
	}
	private static void dfs(int num) {
		vst[num] = true;
		ans++;
		for (int i = 1; i <= N; i++) {
			if(adj[num][i] && !vst[i]) dfs(i);
		}
		
	}

}
