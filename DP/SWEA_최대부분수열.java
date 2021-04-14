import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_최대부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int N, max, nums[], sum[], prefix[];
		
		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			nums = new int[N];
			sum = new int[N];
			prefix = new int[N];
			
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
				
				//부분합 구하기
				if(i>0) sum[i] = sum[i-1] + nums[i];
				else sum[i] = nums[i];
			}
			
			//구간합 구하기
			max = prefix[0] = nums[0];
			for (int i = 1; i < N; i++) {
				prefix[i] = Math.max(prefix[i-1], 0) + nums[i];
				max = Math.max(prefix[i], max);
			}
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

}
