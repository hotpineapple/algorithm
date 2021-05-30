import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697_숨바꼭질 {

	static int N, K;
	static boolean[]vst;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 수빈
		K = Integer.parseInt(st.nextToken()); // 동생
		
		vst = new boolean[100000];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		
		int[] dr = {-1,1};

		int ans = 0;
		OUTER:while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int r = q.poll();
						
				if(r == K) break OUTER; // 동생을 찾으면 반복 종료
				
				// 걷기
				for (int d = 0; d < 2; d++) {
					int nr = r + dr[d];
					
					if(nr < 0 || nr == 100001) continue; //범위 체크
					
					if(!vst[nr]) { // 방문체크 & 빈칸 체크
						vst[nr] = true;
						q.offer(nr);
					}
				}
				
				// 순간이동
				int nr = 2*r;

				if(nr < 0 || nr > 100000) continue; //범위 체크
				
				if(!vst[nr]) { // 방문체크 & 빈칸 체크
					vst[nr] = true;
					q.offer(nr);
				}
			}
			
			ans++;
		}
		
		System.out.println(ans);
	}

}
