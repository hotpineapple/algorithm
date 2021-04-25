import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11403_경로찾기 {

	static int N;
	static boolean[][] vst;
	static int[][] adj;
	static int[][] output;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		vst = new boolean[N][N];
		adj = new int[N][N];
		output = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			go(i);
		}
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(output[i][j]+ " ");	
			}
			System.out.println();
		}

	}
	
	private static void go(int i) {

		for (int j = 0; j < N; j++) {
			if(adj[i][j] == 1 && !vst[i][j]) dfs(i, j);
		}
		
	}

	private static void dfs(int src, int temp) {

		output[src][temp] = 1;
		vst[src][temp] = true;
		
		for (int j = 0; j < N; j++) {
			if(adj[temp][j] == 1 && !vst[src][j]) dfs(src, j);
		}
		
	}

}
