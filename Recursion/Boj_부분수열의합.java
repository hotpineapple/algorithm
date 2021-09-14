package samsung_a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_TEST {
	static int N,S,seq[],sum,ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		seq = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		makeS(0);
		System.out.println(ans);
		
	}
	private static void makeS(int idx) {
		if(idx==N) return;
		if(sum+seq[idx]==S) ans++;
		
		makeS(idx+1);
		
		sum += seq[idx];
		makeS(idx+1);
		
		sum -= seq[idx];
		
	}
}
