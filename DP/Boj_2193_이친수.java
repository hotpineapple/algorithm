import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] D = new long[n+2][2]; // N 자리일 때 맨 마지막 자리수가 0 또느 1인 이친수 개수
		
		D[1][0] = 0;
		D[1][1] = 1;
		D[2][0] = 1;
		D[2][1] = 0;

		for (int i = 3; i <= n; i++) {
			D[i][0] = D[i-1][0] + D[i-1][1];
			D[i][1] = D[i-1][0];
		}
		
		System.out.println(D[n][0] + D[n][1]);
	}

}
