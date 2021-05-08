package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(input);
		
		int minLo = 0, lo=0;
		int minHi = N-1, hi=N-1;
		int min = Integer.MAX_VALUE;
		while(lo < hi) {
			
      int tmp = input[lo] + input[hi];
			
      if(Math.abs(tmp) < min) {
				minLo = lo;
				minHi = hi;
				min = Math.abs(tmp);
				
				if(tmp==0) break;
				
			}
      
			if(tmp < 0) lo++;
			else hi--;
		}
		
		System.out.println(input[minLo] + " " + input[minHi]);
	}

}
