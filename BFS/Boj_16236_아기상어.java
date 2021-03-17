package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236_아기상어 {

	static int ans,len;
	static int[] target;
	static int N;
	static int eatCnt;
	static int[][] map;
	static int babyShark = 2;
	static int[] start;
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) start = new int[] {i,j};
			}
		}

		while(getFish()) goEat();
		System.out.println(ans);	
	}

	private static void goEat() {

		map[target[0]][target[1]] = 9;
		map[start[0]][start[1]] = 0;
		start = new int[] {target[0],target[1]};
		eatCnt++;
		ans += len-1;
		if(eatCnt == babyShark) {
			babyShark++;
			eatCnt = 0;
		}
	}

	private static boolean getFish() {
		for(int i=0;i<N;i++) Arrays.fill(isVisited[i], false);
		Queue<int[]> q = new ArrayDeque<>();
		len = 0;
		q.offer(start);
		isVisited[start[0]][start[1]]=true;
		int minR = Integer.MAX_VALUE;
		int minC = Integer.MAX_VALUE;
		boolean flag = false;
		while(!q.isEmpty() && !flag) {

			int size = q.size();
			while(--size>=0) {
				
				int[] curr = q.poll();
				if(map[curr[0]][curr[1]]>0 && map[curr[0]][curr[1]]<babyShark) {
					flag = true;
					if(curr[0] < minR) {
						minR = curr[0];
						minC = curr[1];
					}else if(curr[0] == minR && curr[1] < minC) {
						minC = curr[1];
					}
					
					continue;
				}
				
				int[] dr = {-1,1,0,0};
				int[] dc = {0,0,-1,1};
	
				for(int d=0;d<4;d++) { //자기보다 큰 물고기는 못지나감
					int nr = curr[0]+dr[d];
					int nc = curr[1]+dc[d];
					if(nr>=0 && nr<N && nc>=0 && nc<N && !isVisited[nr][nc] && map[nr][nc] <= babyShark) {
						
						q.offer(new int[] {nr,nc});
						isVisited[nr][nc] = true;
					
					}
				}
				
			}
			len++;
		}
		
		if(minR == Integer.MAX_VALUE ||minC == Integer.MAX_VALUE) {
			return false;
		}
		else {
			target = new int[] {minR, minC};
			return true;
		}
	}

}
