import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] D = new long[n+2];
		
		D[1] = 1;
		D[2] = 2;
		
		for (int i = 3; i <= n; i++) {
			D[i] = (D[i-1] + D[i-2]) % 10007;
		}
		
		System.out.println(D[n]);
	}

}
