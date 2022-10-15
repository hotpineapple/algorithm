# 각 카드가 몇장인지 저장하는 배열을 이용해서 시간 초과 피하기
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M, input[],output[],cnt[];
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		N = Integer.parseInt(br.readLine());
 		input = new int[N];
 		cnt = new int[20000001];
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		for(int i=0;i<N;i++) {
 			int num = Integer.parseInt(st.nextToken());
 			input[i] = num;
 			cnt[num+10000000]++;
 		}
 		M = Integer.parseInt(br.readLine());
 		output = new int[M];
 		st = new StringTokenizer(br.readLine());
 		for(int i=0;i<M;i++) output[i] = Integer.parseInt(st.nextToken());
 		Arrays.sort(input);
 		StringBuilder sb = new StringBuilder();
 		for(int i=0;i<M;i++) sb.append(find(i)+" ");
 		System.out.println(sb.toString());
 	}
	private static String find(int i) {
		int l=0,r=N-1;
		while(l<=r) {
			int mid = (l+r)/2;
			if(input[mid]<output[i]) {
				l=mid+1;
			}else if(input[mid]>output[i]) {
				r=mid-1;
			}else {
				return cnt[input[mid]+10000000]+"";
			}
		}
		return "0";
	}
 }
```
