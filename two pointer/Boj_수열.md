# 수열
- min 값 설정에유의
```java
package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static long input[];
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		N = Integer.parseInt(st.nextToken());
 		K = Integer.parseInt(st.nextToken());
 		input = new long[N];
 		st = new StringTokenizer(br.readLine());
 		for(int i=0;i<N;i++) input[i]=Integer.parseInt(st.nextToken());
 		
 		// 투포인터
 		int max=-10000000;
 		for(int i=0;i<N-K+1;i++) {//N=10,K=2 이면 i범위 0~8
 			int sum=0;
 			for(int j=i;j<i+K;j++) {// j범위 i~i+1
 				sum +=input[j];
 			}
// 			System.out.println(sum);
 			max = Math.max(max, sum);
 		}
 		System.out.println(max);
	}
 }

```
