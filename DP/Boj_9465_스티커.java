import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_스티커 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int i=0;i<TC;i++) {
			
			// 입력
			int n = Integer.parseInt(br.readLine());
			int[][] score = new int[2][n];
			StringTokenizer st = null;
			
			for(int j=0;j<2;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<n;k++) {
					score [j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			// DP 테이블 생성, 초기값 할당
			int[][] D = new int[3][n];
			D[0][0] = 0;
			D[1][0] = score[0][0];
			D[2][0] = score[1][0];
			
			// DP
			for(int j=1;j<n;j++) {
				D[0][j] = Math.max(D[1][j-1], D[2][j-1]); 
				D[1][j] = Math.max(D[0][j-1], D[2][j-1]) + score[0][j]; 
				D[2][j] = Math.max(D[0][j-1], D[1][j-1]) + score[1][j]; 
			}
			
			// 출력 모으기
			sb.append(Math.max(D[1][n-1], D[2][n-1])).append("\n");
		}
		
		// 출력
		System.out.println(sb.toString());
	}

}
