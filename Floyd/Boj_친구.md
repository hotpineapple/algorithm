# 친구(2-친구)
- 몇다리 건너 친구인지를 계산, 최소값을 취함에 유의
```java
package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int adj[][];
	static char input[][];
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		N = Integer.parseInt(br.readLine());
 		adj = new int[N][N];
 		input = new char[N][N];
 		String str = null;
 		for(int i=0;i<N;i++) {
 			str = br.readLine();
 			for(int j=0;j<N;j++) {
 				input[i][j] = str.charAt(j);
 			}
 		}
 		final int INF = Integer.MAX_VALUE;
 		for(int i=0;i<N;i++) {
 			for(int j=0;j<N;j++) {
 				adj[i][j] = input[i][j]=='N'?INF:1;
 			}
 		}
 		
 		// 경-출-도
 		for(int k=0;k<N;k++) {
 			for(int i=0;i<N;i++) {
 				for(int j=0;j<N;j++) {
 					if(i==j) continue;
 		 			if(adj[i][j]==INF&&adj[i][k]!=INF && adj[k][j]!=INF) adj[i][j]=Math.min(adj[i][j],adj[i][k]+adj[k][j]);
 		 		}
 	 		}
 		}
// 		for(int i=0;i<N;i++) {
// 			for(int j=0;j<N;j++) {
// 				System.out.print(adj[i][j]==INF?0+" ":adj[i][j]+" ");
// 			}
// 			System.out.println();
// 		}
 		int max=-1;
 		for(int i=0;i<N;i++) {
 			int cnt=0;
			for(int j=0;j<N;j++) {
				if(adj[i][j]==2||adj[i][j]==1) cnt++;
			}
			max=Math.max(cnt,max);
 		}

 		System.out.println(max);
	}
 }

```
