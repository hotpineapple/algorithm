import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1003_피보나치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); 

		for (int i = 0; i < T; i++) {
			int[] ans = new int[2];
			int[][] D = new int[41][2];
			D[0][0] = 1;
			D[0][1] = 0;
			D[1][0] = 0;
			D[1][1] = 1;
			
			int num = Integer.parseInt(br.readLine());
			for (int j = 2; j <= num; j++) {
				D[j][0] = D[j-1][0] + D[j-2][0];
				D[j][1] = D[j-1][1] + D[j-2][1];
			}
			System.out.println(D[num][0]+" "+D[num][1]);
		}	
		
	}


}
