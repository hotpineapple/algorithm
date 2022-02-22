import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_동전2 {
	static int N, K, value[], D[];
	static final int MAX_COIN_VALUE = 1000000000;
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		value = new int[N];
		D = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i <= K; i++) {
			D[i] = -1;
		}
		
		// dfs w/메모이제이션
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if(value[i] <= K) {
				ans = Math.min(ans, 1 + dfs(value[i], K - value[i]));
			}
		}
		
		// 출력
		System.out.println(ans >= MAX_COIN_VALUE ? -1 : ans);
	}
	private static int dfs(int n, int k) {
		
		// 기저조건 1
		if(k==0) return 0;
		
		// 기저조건2
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(value[i] <= k) cnt++;
		}
		if(cnt == 0) return MAX_COIN_VALUE;
		
		// 공통 처리
		int res = MAX_COIN_VALUE;
		for (int i = 0; i < N; i++) {
			if(value[i] <= k) {
				if(D[k-value[i]] != -1) res = Math.min(res, 1 + D[k - value[i]]); // 메모이제이션
				else res = Math.min(res, 1 + dfs(value[i], k - value[i]));
			}
		}
		return D[k] = res;
	}
}
