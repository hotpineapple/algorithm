import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int max;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ArrayList<int[]> virus = new ArrayList<>();
		ArrayList<int[]> blank = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				//0: 빈칸 , 1 : 벽, 2 : 바이러스
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blank.add(new int[] {i,j}); //바이러스 위치 저장
				else if(map[i][j] == 2) virus.add(new int[] {i,j}); //바이러스 위치 저장
			}
		}
		
		//벽은 무조건 3개 세워야 함
		//안전영역의 최대넓이 출력
		int size = blank.size();
		int[] P = new int[size]; //N크기의 flag 배열
		int cnt=0;
		while(++cnt<=3) P[size-cnt]=1; //0000 ... 0111
		do {
			for(int i = 0; i<size; i++) {
				if(P[i] == 1) {
					makeWall(blank.get(i));
				}
			}
//			System.out.println("after make wall");
//			print();
			spread(virus);
//			System.out.println("after spread");
//			print();
			getArea();
//			System.out.println("after put back");
			back();
//			print();
			
		}while(nextPermutation(P, size));
		
		System.out.println(max);
	}

	private static void back() {
		int N = map.length;
		int M = map[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 3 || map[i][j] == 4) map[i][j] = 0;
			}
		}
		
	}

	private static void getArea() {
		int N = map.length;
		int M = map[0].length;
		int area = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) area++;
			}
		}
		max = Math.max(max, area);
		
	}

	private static void spread(ArrayList<int[]> virus) {
		int N = map.length;
		int M = map[0].length;
		for(int[] pos : virus) { //모든 바이러스에 대해 상하좌우 퍼져나감
			
			Queue<int[]> q= new ArrayDeque<>();
			boolean[] isVisited = new boolean[N*M];
			q.offer(pos);
			isVisited[M*pos[0]+pos[1]] = true;
			
			int[] dr = {-1,1,0,0};
			int[] dc = {0,0,-1,1};
			
			while(!q.isEmpty()) {
				int[] curr = q.poll();
				map[curr[0]][curr[1]] = 4;
				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];
					if(nr>=0 && nr<N && nc>=0 && nc<M && !isVisited[nr*M + nc] && map[nr][nc] == 0) {
						q.offer(new int[] {nr, nc});
						isVisited[nr*M + nc] = true;
					}
				}
			}
		}
	}

	private static void makeWall(int[] pos) {
		map[pos[0]][pos[1]] = 3;		
	}

	private static boolean nextPermutation(int[] arr, int n) {
		int i = n-1;
		while(i>0 && arr[i-1] >= arr[i]) --i; //1. 꼭대기와 바꿀 애1 찾기
		
		//(1) i>0 조건으로 while문 빠져나온 경우 : 더 이상 앞자리가 없음, 현 순열이 가장 큰 순열 즉 마지막 순열임.
		if(i==0) return false;
		//(2) input[i-1] >= input[i] 조건으로 while문 빠져나온 경우 : i-1가 바꿀위치 중 하나임
		
		int j = n-1;
		while(arr[i-1]>=arr[j]) --j; //2.바꿀 애 2 찾기
		
		swap(arr, i-1,j); //3. 바꾸기
		
		//4. 오름차순 정렬하기
		int k = n-1;
		while(i<k) { 
			swap(arr, i++,k--);
		}
	
		return true;
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void print() {
		int N = map.length;
		int M = map[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
