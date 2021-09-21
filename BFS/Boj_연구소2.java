import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M,wall,ans=99;
	static int[][] map;
	static int[][] output;
	static List<int[]> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		output = new int[M][];
		map = new int[N][N];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==2) list.add(new int[] {i,j});
				else if(num==1)wall++;
			}
		}
	
		combination(0,0);
		
		System.out.println(ans==99?-1:ans);
	}
	private static void combination(int cnt, int start) {
		if(cnt==M) {
//			System.out.println(Arrays.deepToString(output));
			move();
			return;
		}
		for (int i = start; i < list.size(); i++) {
			output[cnt] = list.get(i);
			combination(cnt+1,i+1);
		}
		
	}
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,-1,0,1};
	private static void move() {
		//바이러스 위치에서 시작해서 bfs 돔
		int cnt = N*N-wall; //벽 제외 칸 개수
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] vst = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			int[] virus = output[i];
			cnt--;
			vst[virus[0]][virus[1]] = true;
			q.offer(virus);
		}
		
		int level = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] curr = q.poll();
				int r = curr[0];
				int c = curr[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr<0 || nc<0 ||nr==N||nc==N || map[nr][nc]==1 || vst[nr][nc]) continue;
					cnt--;
					vst[nr][nc] = true;
					q.offer(new int[] {nr,nc});
				}
			}
			level++;
		}
		// 다 퍼졌는지 확인
//		System.out.println("cnt:"+cnt);
		if(cnt==0) ans = Math.min(ans, level-1);
		
	}
}
