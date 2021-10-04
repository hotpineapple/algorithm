# 키순서
### 어려운 점
* 최단경로 x 연결성만 확인하면 됨
* 작 or 큰 두가지 연결 종류

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Boj_fireball {
	
	static int N,M,ans;
	static int[][] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		//입력
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int shorter = Integer.parseInt(st.nextToken());
			int taller = Integer.parseInt(st.nextToken());
			
      //인접행렬 구성
      dist[shorter][taller] = 1; //작
			dist[taller][shorter] = 2; //큰
		}
		
		//플로이드
		for(int k = 1; k <= N ; k++) { 
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) { 
					if(i!=j && dist[i][k]==1 && dist[k][j]==1) dist[i][j] = 1;
					else if(i!=j && dist[i][k]==2 && dist[k][j]==2) dist[i][j] = 2;
				}
			}
		}
		
		// 자신이 몇 번째인지 아는 학생 체크
		for (int i = 1; i <= N; i++) {
			int cnt=0;
			for (int j = 1; j <= N; j++) {
				if(i!=j && dist[i][j] !=0) { // 다른 모든 학생들과 비교결과(작or큰) 있으면 몇번째인지 아는거임
					cnt++;
				}
			}
			if(cnt==N-1) ans++;
		}
		
		//출력
		System.out.println(ans);
		
		   	
	}
	
}
