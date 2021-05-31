import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][][] memo = new int[21][21][21];;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1 && c == -1)
				break;
			
			int res = w(a, b, c);	
			
			System.out.println("w(" + a + ", " + b + ", " + c + ") = " + res);
		}

	}

	private static int w(int a, int b, int c) {

		if(a <= 0 || b <=0 || c <= 0) return 1;
		
		if(a > 20 || b > 20 || c > 20) {
			return memo[20][20][20] = w(20, 20, 20);
		}
		
		if(a < b && b < c) {
			if(memo[a][b][c] > 0) return memo[a][b][c];
			return memo[a][b][c] = w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c);
		}
		
		if(memo[a][b][c] > 0) return memo[a][b][c];
		return memo[a][b][c] = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1);
		
			
	}

}
