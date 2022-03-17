import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
	
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] =  Integer.parseInt(st.nextToken());
		}
		
		// 두포인터
		int s = 0, e = 0, cnt = 0;
		if(arr[0]==1) cnt++;
		
		int min = Integer.MAX_VALUE;
		while(e<N) {
//			System.out.println("s,e,cnt:"+s+","+e+","+cnt);
			if(cnt==K) {
				// 최소크기 갱신
				min = Math.min(min, e-s+1);
				
				// 포인터 이동
				if(e<N) {
					if(arr[s]==1) {
						cnt--;
					}
					s++;
				} else break;
				
			}else if(cnt < K) {
				
				e++;
				if(e<N && arr[e]==1) cnt++;
				
			}
			
		}
		if(min!=Integer.MAX_VALUE) System.out.println(min);
		else System.out.println(-1);
		
	}

}
