import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_adultShark {

	static int N,M,K,ans,cnt,time;
	static Data map[][];
	static Shark[] sharks;
	
	static class Shark {
		int r;
		int c;
		int nr;
		int nc;
		int dir=-1;
		boolean isDead;
		int[][] priority = new int[4][4];
		Shark(int r,int c) {this.r=r;this.c=c;}
	}
	static class Data {
		int sharkNum;
		int smellTime;
		Data() {}
		Data(int sharkNum, int smellTime){
			this.sharkNum=sharkNum;
			this.smellTime = smellTime;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cnt = M;
		map = new Data[N][N];
		sharks = new Shark[M];
		for (int i = 0; i < N; i++) { // 초기 위치
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num!=0) {
					map[i][j] = new Data(num,K);
					sharks[num-1] = new Shark(i,j);
				}else {
					map[i][j] = new Data(num,0);
				}
			}
		}
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < M; i++) {  // 초기 방향
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) { // 상어 별 우선순위
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int k = 0; k < 4; k++) {
					sharks[i].priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		while(cnt>1 && time<=1000) {
			//print();
			go();
			time++;
		}
//		print();
		System.out.println(time>1000?-1:time);
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].sharkNum+", "+map[i][j].smellTime+"  ");
			}
			System.out.println();
		}
		System.out.println("===========");
	}

	private static void go() {
		
		for (int i = 1; i <= M; i++) { // 상어마다 반복하되 작은 숫자 번호의 상어부터 움직이고 겹치면 큰 숫자번호 상어 제거
			if(!sharks[i-1].isDead) move(i);
		}
		postOperation(); // 위치 이동
	}

	private static void postOperation() {
		
		// 냄새지속시간감소
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].sharkNum!=0) map[i][j].smellTime--;
				if(map[i][j].smellTime==0) map[i][j].sharkNum = 0;
			}
		}
		
		// 저장해둔 다음위치에 따라 맵 배열 갱신
		for(int i=0;i<M;i++) {
			Shark s = sharks[i];
			
			if(sharks[i].isDead) continue;
			
			if(map[s.nr][s.nc].sharkNum!=0 &&map[s.nr][s.nc].sharkNum!=i+1) {
				s.isDead = true;
				cnt--; //이미 다른 상어가 있으면 제거됨
			}
			else {
				map[s.nr][s.nc] = new Data(i+1,K);
				s.r = s.nr;
				s.c = s.nc;
			}
		}
	}

	static int[] dr = {-1,1,0,0}; //상하좌우순
	static int[] dc = {0,0,-1,1};
	
	private static void move(int idx) {
		Shark s = sharks[idx-1];
		int currDir = s.dir;
		int[] priority = s.priority[currDir-1];
		
		// 냄새없는 곳 찾기
		for (int j = 0; j < 4; j++) { 
			int nextDir = priority[j];
			int nr = s.r + dr[nextDir-1];
			int nc = s.c + dc[nextDir-1];
			
			if(nr<0||nc<0||nr==N||nc==N) continue;
			if(map[nr][nc].sharkNum == 0) {
				operate(idx,nr,nc,nextDir);
				return;
			}
		}
		
		// 자기냄새인 곳 찾기
		for (int j = 0; j < 4; j++) { // 우선순위 순으로 냄새 없거나 자기냄새인 곳 찾기
			int nextDir = priority[j];
			int nr = s.r + dr[nextDir-1];
			int nc = s.c + dc[nextDir-1];
			
			if(nr<0||nc<0||nr==N||nc==N) continue;
			if(map[nr][nc].sharkNum == idx) {
				operate(idx,nr,nc, nextDir);
				return;
			}
		}
	}

	private static void operate(int idx, int nextR, int nextC, int nextDir) {
		Shark s = sharks[idx-1];
		s.dir = nextDir;
		s.nr = nextR;
		s.nc = nextC;
	}

}
