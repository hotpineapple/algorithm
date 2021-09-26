## 마법사 상어와 토네이도
#### 어려웠던 점
* 규칙 찾기
* 규칙 일반화해서 식으로 

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
	static int N,ans;
	static int[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	static class Tornado {
		int r;
		int c;
		int d;
		Tornado(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];

    	StringTokenizer st = null;
    	for (int i = 0; i < N; i++) {
    		 st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	start(new Tornado(N/2,N/2,0));
		System.out.println(ans);
	}
    
	private static void start(Tornado t) {
		int r = t.r;
		int c = t.c;
		int d = t.d;
		
		// 토네이도 종료
		if(r==0 && c==0) return;
	
		// 방향 전환 조건
		if(r-c==1 && c<N/2) {
//			System.out.println(r+","+c+"왼>아래로 방향전환");
			d = 1;
		}else if(r+c==N-1 && r>N/2 && c<N/2){
//			System.out.println(r+","+c+"아래>오로 방향전환");
			d = 2;
		}else if(r==c && r>N/2 && c>N/2){
//			System.out.println(r+","+c+"오>위로 방향전환");
			d = 3;
		}else if(r+c==N-1 &&  r<N/2 && c>N/2) {
//			System.out.println(r+","+c+"위>왼으로 방향전환");
			d = 0;
		}
		
		// 타겟 칸 찾기
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		int sand = map[nr][nc];
		map[nr][nc] = 0;
		int sum=0;
		
		// 모래이동
		if(d==0) {
			
			int num1 = (int) (sand*0.01);
			if(r-1>=0) map[r-1][c] += num1;
			else ans += num1;
			sum += num1;
			
			int num2 = (int) (sand*0.01);
			if(r+1<N) map[r+1][c] += num2;
			else ans += num2;
			sum += num2;
			
			int num3 = (int) (sand*0.07);
			if(nr-1>=0) map[nr-1][nc] += num3;
			else ans += num3;
			sum += num3;
			
			int num4 = (int) (sand*0.07);
			if(nr+1<N) map[nr+1][nc] += num4;
			else ans += num4;
			sum += num4;
			
			int num5 = (int) (sand*0.02);
			if(nr-2>=0) map[nr-2][nc] += num5;
			else ans += num5;
			sum += num5;
			
			int num6 = (int) (sand*0.02);
			if(nr+2<N) map[nr+2][nc] += num6;
			else ans += num6;
			sum += num6;
			
			int num7 = (int) (sand*0.1);
			if(nr-1>=0 && nc-1>=0) map[nr-1][nc-1] += num7;
			else ans += num7;
			sum += num7;
			
			int num8 = (int) (sand*0.1);
			if(nr+1<N && nc-1>=0) map[nr+1][nc-1] += num8;
			else ans += num8;
			sum += num8;
			
			int num9 = (int) (sand*0.05);
			if(nc-2>=0) map[nr][nc-2] += num9;
			else ans += num9;
			sum += num9;
			
			int num10 = sand - sum;
//			int num10 = (int)(sand*0.45);
			if(nc-1>=0) map[nr][nc-1] += num10;
			else ans += num10;
		}else if(d==1) {
			
			int num1 = (int) (sand*0.01);
			if(c-1>=0) map[r][c-1] += num1;
			else ans += num1;
			sum += num1;
			
			int num2 = (int) (sand*0.01);
			if(c+1<N) map[r][c+1] += num2;
			else ans += num2;
			sum += num2;
			
			int num3 = (int) (sand*0.07);
			if(nc-1>=0) map[nr][nc-1] += num3;
			else ans += num3;
			sum += num3;
			
			int num4 = (int) (sand*0.07);
			if(nc+1<N) map[nr][nc+1] += num4;
			else ans += num4;
			sum += num4;
			
			int num5 = (int) (sand*0.02);
			if(nc-2>=0) map[nr][nc-2] += num5;
			else ans += num5;
			sum += num5;
			
			int num6 = (int) (sand*0.02);
			if(nc+2<N) map[nr][nc+2] += num6;
			else ans += num6;
			sum += num6;
			
			int num7 = (int) (sand*0.1);
			if(nr+1<N && nc-1>=0) map[nr+1][nc-1] += num7;
			else ans += num7;
			sum += num7;
			
			int num8 = (int) (sand*0.1);
			if(nr+1<N && nc+1<N) map[nr+1][nc+1] += num8;
			else ans += num8;
			sum += num8;
			
			int num9 = (int) (sand*0.05);
			if(nr+2<N) map[nr+2][nc] += num9;
			else ans += num9;
			sum += num9;
			
			int num10 = sand - sum;
			if(nr+1<N) map[nr+1][nc] += num10;
			else ans += num10;
		}else if(d==2) {
			
			int num1 = (int) (sand*0.01);
			if(r-1>=0) map[r-1][c] += num1;
			else ans += num1;
			sum += num1;
			
			int num2 = (int) (sand*0.01);
			if(r+1<N) map[r+1][c] += num2;
			else ans += num2;
			sum += num2;
			
			int num3 = (int) (sand*0.07);
			if(nr-1>=0) map[nr-1][nc] += num3;
			else ans += num3;
			sum += num3;
			
			int num4 = (int) (sand*0.07);
			if(nr+1<N) map[nr+1][nc] += num4;
			else ans += num4;
			sum += num4;
			
			int num5 = (int) (sand*0.02);
			if(nr-2>=0) map[nr-2][nc] += num5;
			else ans += num5;
			sum += num5;
			
			int num6 = (int) (sand*0.02);
			if(nr+2<N) map[nr+2][nc] += num6;
			else ans += num6;
			sum += num6;
			
			int num7 = (int) (sand*0.1);
			if(nr-1>=0 && nc+1<N) map[nr-1][nc+1] += num7;
			else ans += num7;
			sum += num7;
			
			int num8 = (int) (sand*0.1);
			if(nr+1<N && nc+1<N) map[nr+1][nc+1] += num8;
			else ans += num8;
			sum += num8;
			
			int num9 = (int) (sand*0.05);
			if(nc+2<N) map[nr][nc+2] += num9;
			else ans += num9;
			sum += num9;
			
			int num10 = sand - sum;
			if(nc+1<N) map[nr][nc+1] += num10;
			else ans += num10;
		}else if(d==3) {
			
			int num1 = (int) (sand*0.01);
			if(c-1>=0) map[r][c-1] += num1;
			else ans += num1;
			sum += num1;
			
			int num2 = (int) (sand*0.01);
			if(c+1<N) map[r][c+1] += num2;
			else ans += num2;
			sum += num2;
			
			int num3 = (int) (sand*0.07);
			if(nc-1>=0) map[nr][nc-1] += num3;
			else ans += num3;
			sum += num3;
			
			int num4 = (int) (sand*0.07);
			if(nc+1<N) map[nr][nc+1] += num4;
			else ans += num4;
			sum += num4;
			
			int num5 = (int) (sand*0.02);
			if(nc-2>=0) map[nr][nc-2] += num5;
			else ans += num5;
			sum += num5;
			
			int num6 = (int) (sand*0.02);
			if(nc+2<N) map[nr][nc+2] += num6;
			else ans += num6;
			sum += num6;
			
			int num7 = (int) (sand*0.1);
			if(nr-1>=0 && nc-1>=0) map[nr-1][nc-1] += num7;
			else ans += num7;
			sum += num7;
			
			int num8 = (int) (sand*0.1);
			if(nr-1>=0 && nc+1<N) map[nr-1][nc+1] += num8;
			else ans += num8;
			sum += num8;
			
			int num9 = (int) (sand*0.05);
			if(nr-2>=0) map[nr-2][nc] += num9;
			else ans += num9;
			sum += num9;
			
			int num10 = sand - sum;
			if(nr-1>=0) map[nr-1][nc] += num10;
			else ans += num10;
		}
//		System.out.println("sand: "+ans);
		start(new Tornado(nr,nc,d));
	}
    
}
```
