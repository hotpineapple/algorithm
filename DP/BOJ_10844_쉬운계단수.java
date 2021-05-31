import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10844_쉬운계단수 {
	static int D[][];
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	
		D = new int[101][10];
		
		StringTokenizer st = null;
	
		D[1][0] = 0; // 길이 1이면서 0으로 끝나는 계단수의 개수
		D[1][1] = 1; // 길이 1이면서 1으로 끝나는 계단수의 개수
		D[1][2] = 1;
		D[1][3] = 1;
		D[1][4] = 1;
		D[1][5] = 1;
		D[1][6] = 1;
		D[1][7] = 1;
		D[1][8] = 1;
		D[1][9] = 1; // 길이 1이면서 9으로 끝나는 계단수의 개수
		
		for (int i = 2; i <= N; i++) {
			D[i][0] = D[i-1][1];
			for (int j = 1; j < 9; j++) {
				D[i][j] = (D[i-1][j+1] + D[i-1][j-1]) % 1000000000; 
			}
			D[i][9] = D[i-1][8];
		}
		
		int sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum += D[N][i];
			sum %= 1000000000;
		}
		System.out.println(sum % 1000000000);
	}

}
