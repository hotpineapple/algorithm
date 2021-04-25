package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_불 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];

			Queue<int[]> q1 = new ArrayDeque<>(); // 상근
			Queue<int[]> q2 = new ArrayDeque<>(); // 불
			boolean[][] vst = new boolean[N][M]; // 공통
			
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					map[i][j] = line[j];
					if (map[i][j] == '@') {
						q1.offer(new int[] { i, j });
						vst[i][j] = true;
					} else if(map[i][j]=='*') {
						q2.offer(new int[] { i, j });
						vst[i][j] = true;
					}
				}
			}

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			int level = 1;
			boolean flag = false;
			OUTER: while (!q2.isEmpty()) {
				int size1 = q1.size();
				int size2 = q2.size();

				// 불 먼저 BFS (상근이는 불이 번진 곳과 곧 번질 곳으로 이동 불가능하므로
				while (--size2 >= 0) {
					int[] curr = q2.poll();
					int r = curr[0];
					int c = curr[1];

					for (int d = 0; d < 4; d++) {

						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nc < 0 || nr == N || nc == M) continue;
						
						if (map[nr][nc] == '#' || vst[nr][nc]) continue;

						q2.offer(new int[] { nr, nc});
						vst[nr][nc] = true;						 
					}
				}
				
				// 상근 BFS
				while (--size1 >= 0) {
					int[] curr = q1.poll();
					int r = curr[0];
					int c = curr[1];

					for (int d = 0; d < 4; d++) {

						int nr = r + dr[d];
						int nc = c + dc[d];

						if (nr < 0 || nc < 0 || nr == N || nc == M)  { // 빌딩 탈출하면 종료.
							flag = true;
							break OUTER;
						}
						
						if (map[nr][nc] == '#' || vst[nr][nc]) continue;
					
						q1.offer(new int[] {nr, nc});
						vst[nr][nc] = true;
					}
				}
				level++;
			}

			System.out.println(flag ? level : "IMPOSSIBLE");
		}
	}

}
