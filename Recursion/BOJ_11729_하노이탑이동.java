import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int cnt;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		hanoi(1, 2, 3, N);
		System.out.println(cnt);
		System.out.println(sb.toString());
		
	}
  
	private static void hanoi(int start, int aux, int end, int n) {

		cnt++;
		
		if(n==1) {
			sb.append(start + " " + end + "\n");
			return;
		}
		
		hanoi(start, end, aux, n-1);
		sb.append(start + " " + end + "\n");
		hanoi(aux, start, end, n-1);
		
	}

}
