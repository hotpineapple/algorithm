import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_babyshark2 {
	static class Point {
		int r;
		int c;
		Point() {}
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N,M,map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		List<Point> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) list.add(new Point(i,j));
				map[i][j] = num;
			}
		}
		
		int ans = 0;
		for (int i = 0; i < list.size(); i++) {
			ans = Math.max(ans, bfs(list.get(i)));
		}
		System.out.println(ans);
	}
	private static int bfs(Point p) {

		int[] dr = {-1,-1,-1,0,0,1,1,1};
		int[] dc = {-1,0,1,-1,1,-1,0,1};
		boolean[][] isVisited = new boolean[N][M];
		int dist = 0;
		Queue<Point> q = new ArrayDeque<>();
		isVisited[p.r][p.c] = true;
		q.offer(p);
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Point curr = q.poll();
				
				for(int d=0;d<8;d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					
					if(nr<0||nc<0||nr==N||nc==M||isVisited[nr][nc]) continue;
					
					if(map[nr][nc]==1) { // 인접한 칸에 아기상어가 있다면 bfs 끝냄
						return dist+1;
					}
					
					isVisited[nr][nc] = true;
					q.offer(new Point(nr,nc));
				}
			}
			dist++;
		}

		return dist;
	}
}
