import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Boj_2583_영역구하기 {

	static int[][] map;
	static boolean[][] isVisited;
	static int M,N;
	static ArrayList<Integer> area;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		M = Integer.parseInt(st.nextToken()); 
		N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		isVisited = new boolean[M][N];
		area = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int c1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			for(int j=r1;j<r2;j++) {
				for(int k=c1;k<c2;k++) {
					map[j][k] = 1;
				}
			}
		}

		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(!isVisited[i][j] && map[i][j]==0) {
					area.add(dfs(i,j));
				}
			}
		}
        
		System.out.println(area.size());
		Collections.sort(area);
		Iterator<Integer> it = area.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
	}
	
	private static int dfs(int r, int c) {
		int cnt=1;
		isVisited[r][c] = true;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr<M && nc>=0 && nc<N && !isVisited[nr][nc] && map[nr][nc] == 0) cnt+=dfs(nr,nc);
			
		}
		return cnt;
		
	}
}
