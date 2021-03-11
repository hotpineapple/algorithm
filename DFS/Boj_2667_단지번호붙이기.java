import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Boj_2667_단지번호붙이기 {

	static int N;
	static int[][] map;
	static boolean[][] isVisited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 && !isVisited[i][j]) list.add(dfs(new int[] {i,j}));				
			}
		}
		System.out.println(list.size());
		Iterator<Integer> it = list.iterator();
		Collections.sort(list);
//		while(it.hasNext()) {
//			int num = it.next();
//			System.out.println(num);
//		}
		for(int i=0;i<list.size();i++) System.out.println(list.get(i));

	}

	private static int dfs(int[] curr) {
		int cnt = 1;
//		System.out.println("curr: "+curr[0]+", "+curr[1]);
		isVisited[curr[0]][curr[1]] = true;
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for(int d=0;d<4;d++) {
			int nr = curr[0] + dr[d];
			int nc = curr[1] + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc]==1 && !isVisited[nr][nc]) cnt+= dfs(new int[] {nr,nc});
		}
		return cnt;
	}

}
