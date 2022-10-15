# 랜선자르기
- 자료형에 유의
```java
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
 		K = Integer.parseInt(st.nextToken());
 		N = Integer.parseInt(st.nextToken());
 		input = new long[K];
 		for(int i=0;i<K;i++) input[i]=Integer.parseInt(br.readLine());
 		
 		long l=1,r=(long) (Math.pow(2,31)-1);
 		long ans=0;
 		while(l<=r) {
 			long mid =(l+r)/2;
 			boolean res = isTrue(mid);
// 			System.out.println(mid+": "+res);
 			if(res) {
 				ans=  mid;
 				l=mid+1;
 			}else {
 				r= mid-1;
 			}
 		}
 		System.out.println(ans);
 		
 	}
	private static boolean isTrue(long len) {
		int sum=0;
		for(int i=0;i<K;i++) sum+=input[i]/len;
		return sum>=N;
	}
 }

```
