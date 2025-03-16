# 평범한 가방

- 백준링크: https://www.acmicpc.net/problem/12865
- 다이나믹 프로그래밍은 분할정복과 달리 결과를 재사용하므로 메모이제이션을 이용하여 불필요한 연산을 줄여야 한다.
- 바텀업
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 물품 개수
		int K = Integer.parseInt(st.nextToken()); // 최대 무게
		
		int[] w = new int[N+1];
		int[] v = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		// DP
		int[][] D = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(j < w[i]) { //임시배낭제한보다 물건의 무게가 무거울 때 => 못 넣음
					
					D[i][j] = D[i-1][j];
					
				}else { //
					
					D[i][j] = Math.max(D[i-1][j-w[i]] + v[i], D[i-1][j]);
				}
			}
		}
		
		System.out.println(D[N][K]);
	}
}

```
- 탑다운
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] memo;
    static int[] W,V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken()); // 물품 개수
        int K = Integer.parseInt(st.nextToken()); // 최대 무게

        memo = new int[N+1][K+1];
        W = new int[N+1];
        V = new int[N+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // DP
        System.out.println(go(N,K));
    }
    private static int go(int idx, int weight) {
        if(idx==1) {
            if(weight>=W[idx]) memo[idx][weight] = V[idx];
            return memo[idx][weight];
        }

        int num1 = 0,num2;
        if (weight >= W[idx]) {
            if(memo[idx-1][weight-W[idx]]!=0) num1= memo[idx-1][weight-W[idx]]+V[idx];
            else num1 = go(idx - 1, weight - W[idx])+V[idx];
        }

        if(memo[idx-1][weight]!=0) num2=memo[idx-1][weight];
        else num2 = go(idx-1,weight);

        memo[idx][weight] = Math.max(num1,num2);

        return memo[idx][weight];
    }
}
```
