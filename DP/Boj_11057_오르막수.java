import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, D[][];
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		D = new int[N+1][10]; // 행: 숫자 길이, 열: 마지막 자리 수(0~9) -> 에 따른  오르막수 개수
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				D[i][j] = -1;
			}
		}
		
		int ans = 0;
		for (int i = 0; i <=9; i++) {
			ans += dfs(N, i);
		}
		
		System.out.println(ans % 10007);
	}
	private static int dfs(int n, int m) {
		if(n == 1) return 1;
		
		int res = 0;
		for (int i = 0; i <= 9; i++) {
			if(i > m) break;
			
			if(D[n-1][i] != -1) {
				res += D[n-1][i];
			} else {
				res += dfs(n-1, i);
			}
		}
		return D[n][m] = res % 10007;
	}
}
