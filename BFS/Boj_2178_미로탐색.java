import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,map[][];
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		N = Integer.parseInt(st.nextToken());
 		M = Integer.parseInt(st.nextToken());
 		map = new int[N][M];
 		String str = null;
 	 	for(int i=0;i<N;i++) {
 	 		str = br.readLine();
 	 		for(int j=0;j<M;j++) {
 	 			map[i][j] =  Integer.parseInt(str.charAt(j)+"");
 	 		}
 	 	}

 	 	boolean[][] vst = new boolean[N][M];
 		int[] dr = {-1,1,0,0};
 		int[] dc = {0,0,-1,1};
 	 	int cnt=0;
 	 	Queue<int[]> q = new LinkedList<>();
 	 	vst[0][0] = true;
 	 	q.offer(new int[] {0,0});
 	 	OUTER: while(!q.isEmpty()) {
 	 		int size = q.size();
 	 		while(size-->0) {
 	 			int[] cur = q.poll();
 	 			int r = cur[0];
 	 			int c = cur[1];
// 	 			System.out.println(r+","+c);
 	 			if(r==N-1&&c==M-1) {
 	 				cnt++;
 	 				break OUTER;
 	 			}
 	 			
 	 			for(int d=0;d<4;d++) {
 	 				int nr = r+dr[d];
 	 				int nc = c+dc[d];
 	 				if(nr<0||nr>=N||nc<0||nc>=M) continue;
 	 				if(map[nr][nc]==0) continue;
 	 				if(vst[nr][nc]) continue;
 	 				vst[nr][nc] = true;
 	 				q.offer(new int[] {nr,nc});
 	 			}	
 	 		}
 	 		cnt++;
// 	 		System.out.println("============");
 	 	}
 	 	System.out.println(cnt);
	}
 }
