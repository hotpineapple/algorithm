# 주사위 굴리기
- 주사위 굴리는 로직이 중요
```java
package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K, cmds[], map[][];
	static Dice dice;
	static class Dice {
		int r;
		int c;
		int up;
		int north;
		int east;
		int west;
		int south;
		int down;
		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		
 		N = Integer.parseInt(st.nextToken());
 		M = Integer.parseInt(st.nextToken());
 		dice = new Dice(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
 		K = Integer.parseInt(st.nextToken());
 		
 		map = new int[N][M];
 		for(int i=0;i<N;i++) {
 			st = new StringTokenizer(br.readLine());
 			for(int j=0;j<M;j++) {
 				map[i][j] = Integer.parseInt(st.nextToken());
 			}
 		}
 		
 		cmds = new int[K];
 		st = new StringTokenizer(br.readLine());
 		for(int i=0;i<K;i++) {
 			cmds[i] = Integer.parseInt(st.nextToken());
 		}
 		
 		// 처리
 		for(int i=0;i<K;i++) {
 			go(i);
 		}
 	}
	private static void go(int idx) {
		int cmd = cmds[idx];
		// 위치 조정
		int[] dr = {0,0,0,-1,1};
		int[] dc = {0,1,-1,0,0};
		
		int nr = dice.r + dr[cmd];
		int nc = dice.c + dc[cmd];
		
		// 범위체크
		if(nr<0||nr>=N||nc<0||nc>=M) return;
		
		dice.r = nr;
		dice.c = nc;
		
		// 굴리기
		if(cmd==1) {// 동
			int temp = dice.up;
			dice.up = dice.west;
			dice.west = dice.down;
			dice.down = dice.east;
			dice.east = temp;
		}else if(cmd==2) { //서
			int temp = dice.up;
			dice.up = dice.east;
			dice.east = dice.down;
			dice.down = dice.west;
			dice.west = temp;
		}else if(cmd==3) { //북
			int temp = dice.up;
			dice.up = dice.south;
			dice.south = dice.down;
			dice.down = dice.north;
			dice.north = temp;
		}else if(cmd==4) { //남
			int temp = dice.up;
			dice.up = dice.north;
			dice.north = dice.down;
			dice.down = dice.south;
			dice.south = temp;
		}
		
		// 숫자 복사
		if(map[nr][nc]==0) {
			map[nr][nc] = dice.down;
		}else {
			dice.down = map[nr][nc];
			map[nr][nc] = 0;
		}
		
		// 출력
		System.out.println(dice.up);
	}
 }

```
