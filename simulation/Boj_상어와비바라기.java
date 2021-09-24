
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
	static int N,M,ans;
	static Move[] arr;
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {-1,-1,0,1,1,1,0,-1};
	static Cell[][] map;
	
	static class Move {
		int d;
		int s;
		Move(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	static class Cell {
		boolean hasCloud; // 이 칸에 구름이 있는지 여부
 		int water; // 물의 양
		int diameterCnt; // 대각선 인접한 물이 있는 칸의 수
		boolean isTarget; // 물복사버그 시전 대상인지 여부
		Cell(int water){
			this.water = water;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new Cell[N][N];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = new Cell(Integer.parseInt(st.nextToken())); 
			}
		}
    	
    	arr = new Move[M];
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine()," ");
    		arr[i] = new Move(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
    	
    	// 초기 구름 위치 세팅
    	map[N-1][0].hasCloud = true;
    	map[N-1][1].hasCloud = true;
    	map[N-2][0].hasCloud = true;
    	map[N-2][1].hasCloud = true;
    	
    	// 구름 M번 이동 명령
    	for (int i = 0; i < M; i++) {
			int d = arr[i].d-1;
			int s = arr[i].s;
			moveCloud(d,s);
			rain();
			copyMagic();
			makeCloud();
		}
    	ans = count();
    	System.out.println(ans);
	}
	private static int count() {
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j].water;
			}
		}
		return sum;
	}
	private static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].water>=2 && !map[i][j].isTarget) {
					map[i][j].isTarget = false;
					map[i][j].water -=2;
					map[i][j].hasCloud = true;
				}
				map[i][j].isTarget = false;
			}
			
		}
		
	}
	private static void copyMagic() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].isTarget) {
					
					int cnt=0;
					for (int d = 1; d < 8; d=d+2) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr<0||nc<0||nr==N||nc==N) continue;
						if(map[nr][nc].water<=0) continue;
						cnt++;
					}
					map[i][j].diameterCnt = cnt;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].water += map[i][j].diameterCnt;
				map[i][j].diameterCnt=0;
			}
		}
	}
	private static void rain() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].hasCloud) {
					map[i][j].water++;
					map[i][j].hasCloud = false;
					map[i][j].isTarget = true;
				}
			}
		}
		
	}
	private static void moveCloud(int d, int s) {
		List<int[]> prev = new ArrayList<>();
		List<int[]> next = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!map[i][j].hasCloud) continue;
				
				prev.add(new int[] {i,j});
				
				int nr = i + dr[d]*s;
				int nc = j + dc[d]*s;
				
				if(nr<0) while(nr<0)nr += N;
				if(nc<0) while(nc<0)nc += N;
				if(nr>=N) while(nr>=N)nr -= N;
				if(nc>=N) while(nc>=N)nc -= N;
				
				next.add(new int[] {nr,nc});
				
			}
		}
		for (int j = 0; j < prev.size(); j++) {
			int[] pos = prev.get(j);
			map[pos[0]][pos[1]].hasCloud = false;
		}
		for (int j2 = 0; j2 < next.size(); j2++) {
			int[] pos = next.get(j2);
			map[pos[0]][pos[1]].hasCloud = true;
		}
	}

	
}
