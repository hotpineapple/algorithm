# 맥주마시면서걸어가기

### 어려웠던 점
* 플로이드 와샬 알고리즘 사용하는 걸 알아채기
* 근거
  * N 이 100이하
  * 편의점 몇개 들르든 상관없음. 단, 모든 편의점을 따져보기만 하면됨(DP)
* 특이점 
  * 각 편의점에서 맥주를 몇병 조달하든 상관없음 -> 인접행렬 직접 완성하기
  * 최단거리가 아니라 연결가능성만 확인하면 됨

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N;
	static Pos[] arr;
	static int[][] dist;
	static class Pos {
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 0; tc < TC; tc++) {
		//입력
    		N = Integer.parseInt(br.readLine()); //편의점 개수
		arr = new Pos[N+2];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		arr[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		dist = new int[N+2][N+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			arr[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine()," ");
		arr[N+1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		//인접행렬 만들기 - 편의점 들를때마다 맥주는 풀로 조달한다고 가정함 -> 거리 1000 이하면 연결 처리함
		for (int i = 0; i <= N+1; i++) {
			for (int j = 0; j <= N+1; j++) {
				int temp = Math.abs(arr[i].x-arr[j].x) + Math.abs(arr[i].y-arr[j].y); //맨하탄거리
				if(temp <= 1000) dist[i][j] = 1;
			}
		}

		//플로이드
		for(int k = 0; k <= N+1 ; k++) { 
			for(int i = 0; i <= N+1; i++) {
				for(int j = 0; j <= N+1; j++) { 
					if(dist[i][k]==1 && dist[k][j]==1) dist[i][j] = 1;
				}
			}
		}

		//출력
		System.out.println(dist[0][N+1]==1?"happy":"sad");
		}
		   	
	}
	
}
