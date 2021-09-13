import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_nm1 {

	static int N, M, output[];
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N];
		output = new int[M];
		
		permutation(0);

	}

	private static void permutation(int cnt) {
		if(cnt==M){
			print(output);
			return;
		}
		
		for(int i=1; i<=N; ++) {
			if(isSelected[i-1]) continue;
      
			isSelected[i-1] = true;
			output[cnt] = i;
			permutation(cnt+1);
			isSelected[i-1] = false;
		}
	}

	private static void print(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

}
