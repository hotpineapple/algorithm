import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_nm2 {

	static int N, M, output[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		output = new int[M];
		
		combination(0,1);

	}

	private static void combination(int cnt, int start) {
		if(cnt==M){
			print(output);
			return;
		}
		
		for(int i=start;i<=N;i++) {
			output[cnt] = i;
			combination(cnt+1,i+1);
			
		}
	}

	private static void print(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

}
