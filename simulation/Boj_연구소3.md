## 연구소 3
#### 어려웠던 점
* 비활성 바이러스도 활성화 되어 추후에 빈 공간으로 퍼질 수 있다는 점
* 그러나 비활성 바이러스들만 활성화 되는 데 소요된 시간은 무의미한 시간이라는 점
* 이를 해결하기 위해 Cell 클래스를 만들어 그 칸 까지 가는 시간과 함께 원래 어떤 칸이었는지를 저장해두고, 빈 칸이었던 칸에 대해서만 time을 계산했다.

````
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	static int N,M,rest,ans=Integer.MAX_VALUE;
	static Cell[][] map,map2;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	static List<int[]> input = new ArrayList<>();
	static int[][] output;
	static boolean[][] vst;
	
	static enum Type {
		Empty,
		Wall,
		Inactive
	}
	static class Cell{
		int time;
		Type type; // 빈공간이면 true, 비활성 바이러스 공간이면 false;
		Cell(int time, Type type){
			this.time = time;
			this.type = type;
		}
	}
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st =  new StringTokenizer(br.readLine()," ");
    	N =Integer.parseInt(st.nextToken());
    	M =Integer.parseInt(st.nextToken());
    	map = new Cell[N][N];
    	map2 = new Cell[N][N];
    	output = new int[M][];
    	
    	for (int i = 0; i < N; i++) {
    		 st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num==2) {
					input.add(new int[] {i,j});
					map2[i][j] = map[i][j] = new Cell(num, Type.Inactive);
				}else if(num==0) {
					map2[i][j] = map[i][j] = new Cell(num,Type.Empty);
					rest++;
				}else if(num==1) {
					map2[i][j] = map[i][j] = new Cell(num,Type.Wall);
				}
				
			}
		}
    	if(rest==0) {
    		System.out.println(0);
    		return;
    	}
    	combi(0,0);
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}

	private static void combi(int cnt, int start) {
		if(cnt==M) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = map2[i][j];
				}
			}
			vst = new boolean[N][N];
			spread(output);
			return;
		}
		
		for (int i = start; i < input.size(); i++) {
			output[cnt]=input.get(i);
			combi(cnt+1,i+1);
		}
	}

	private static void spread(int[][] virus) {
		
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			int[] pos= virus[i];
			vst[pos[0]][pos[1]] = true;
			q.offer(pos);
		}
		
		int time=0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] cur = q.poll();
				map[cur[0]][cur[1]].time = time;
				
				for (int d = 0; d < 4; d++) {
					int nr = cur[0]+dr[d];
					int nc = cur[1]+dc[d];
					
					if(nr<0||nc<0||nr==N||nc==N) continue;
					if(map[nr][nc].type == Type.Wall) continue;				
					if(vst[nr][nc]) continue;
					
					vst[nr][nc] = true;
					q.offer(new int[] {nr,nc});

				}
			}
			time++;
		}
		
		// 다 못퍼뜨리면 -1임 주의
		int result=0;
		outer:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].type==Type.Empty) {
					if(!vst[i][j]) {
						time = -1;
						break outer;
					}else {
						result = Math.max(map[i][j].time, result);
					}
				}
			}
		}
		if(time!=-1) ans = Math.min(ans, result);
	}
   
}
```
