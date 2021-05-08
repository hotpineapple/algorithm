import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두수의합 {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		int target = Integer.parseInt(br.readLine());
		
    //오름차순 정렬
		Arrays.sort(input);
		
    int lo = 0, hi = N-1;
		int ans = 0;
		while(lo < hi) {
			int tmp = input[lo]+input[hi];
			if(tmp == target) {
				ans++;
				lo++;
				hi--;
			}
      else if(tmp < target)lo++;
			else hi--;
			
		}
		
		System.out.println(ans);
	}

}
