
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M, input[], output[];
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		N = Integer.parseInt(br.readLine());
 		input = new int[N];
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		for(int i=0;i<N;i++) input[i] = Integer.parseInt(st.nextToken());
 		M = Integer.parseInt(br.readLine());
 		output = new int[M];
 		st = new StringTokenizer(br.readLine());
 		for(int i=0;i<M;i++) output[i] = Integer.parseInt(st.nextToken());
 		
 		Arrays.sort(input);
 		for(int i=0;i<M;i++) find(i);
 	}
	private static void find(int i) {
		int num = output[i];
		int l=0,r=N-1;
		while(l<=r) {
			int mid = (l+r)/2;
			if(input[mid]<num) {
				l=mid+1;
			}else if(input[mid]>num) {
				r=mid-1;
			}else {
				System.out.println("1");
				return;
			}
		}
		System.out.println("0");
	}
 }
