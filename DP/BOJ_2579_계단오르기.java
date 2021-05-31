import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int input[], D[][];
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		input = new int[301];
		D = new int[301][2];
		
		StringTokenizer st = null;
		
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		D[1][0] = input[1]; // 전 계단에서 그냥 올라온 경우
		D[1][1] = 0; // 전전 계단에서 건너뛰어 올라온 경우
		D[2][0] = input[2] + D[1][0];
		D[2][1] = input[2] + D[0][0];
		
		for (int i = 3; i <= N; i++) {
			D[i][0] = input[i] + D[i-1][1]; //전 계단의 건너뛰어 올라온 경우 점수 최대값 
			D[i][1] = input[i] + Math.max(D[i-2][0], D[i-2][1]); // 전전 계단의 그냥 올라온 경우와  건너뛰어 올라온 경우의 최대값 중 더 큰 값을 더함
		}
		
		System.out.println(Math.max(D[N][0], D[N][1]));
	}

}
