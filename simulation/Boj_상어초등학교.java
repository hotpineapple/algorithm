import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
    static int N;
    static Shark[][] map;
    static class Shark {
    	int num;
    	int[] likes;
    	public Shark (int num, int[] likes) {
    		this.num = num;
    		this.likes = likes;
    	}
    }
    static Shark[] sharkArr;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new Shark[N][N];
        sharkArr = new Shark[N*N];
        StringTokenizer st = null;
        for (int i = 0; i < N*N; i++) {
        	st = new StringTokenizer(br.readLine()," ");
        	int num = Integer.parseInt(st.nextToken());
        	int[] likes = new int[] {
        			Integer.parseInt(st.nextToken()),
        			Integer.parseInt(st.nextToken()),
        			Integer.parseInt(st.nextToken()),
        			Integer.parseInt(st.nextToken())
        	};
        	sharkArr[i] = new Shark(num,likes);
		}
       
        for (int i = 0; i < N*N; i++) findSeat(sharkArr[i]);
//        print();
        calScore();
 
    }
    private static void print() {

    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { // 각 칸마다 확인
				System.out.print(map[i][j].num+" ");
			}
			System.out.println();
    	}
		
	}
	private static void calScore() {
		int score = 0;
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { // 각 칸마다 확인
				int cnt=0;
				Shark s = map[i][j];
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<0||nc<0||nr==N||nc==N) continue;
					for (int k = 0; k < 4; k++) {
						if(map[nr][nc].num == s.likes[k]) cnt++;
					}
				}
				if(cnt==1)score+=1;
				else if(cnt==2)score+=10;
				else if(cnt==3)score+=100;
				else if(cnt==4)score+=1000;
			}
    	}
    	System.out.println(score);
	}
	static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
	private static void findSeat(Shark shark) {
		
		List<int[]> candidates = new ArrayList<>();
		int max = 0;
		// 1. 인접한 칸에 좋아하는 학생 번호 개수 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { // 각 칸마다 확인
				if(map[i][j]!=null) continue; // 빈칸 아니면 건너뜀
				int like=0;
				int empty = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<0||nc<0||nr==N||nc==N) continue;
					if(map[nr][nc]==null) empty++;
					for (int k = 0; k < 4; k++) {
						if(map[nr][nc]!=null && map[nr][nc].num == shark.likes[k]) like++;
					}
				}
				if(like > max) {
					max = like;
					candidates.clear();
					candidates.add(new int[] {i,j,empty});
				}else if(like == max) {
					candidates.add(new int[] {i,j,empty});
				}
			}
		}
		
		// 1번 조건에 맞는 칸이 몇개인지 확인
		if(candidates.size()==1) { //1개인경우 그 칸이 학생자리
			map[candidates.get(0)[0]][candidates.get(0)[1]] = shark;
			return;
		}
		
		// 1번조건에 맞는 칸이 여러개인 경우 2번 조건. 비어있는 칸의 개수 확인
		List<int[]> candidates2 = new ArrayList<>();
		int max2 =0;
		for (int i = 0; i < candidates.size(); i++) {
			int empty = candidates.get(i)[2];
			if(empty>max2) {
				max2 = empty;
				candidates2.clear();
				candidates2.add(new int[] {candidates.get(i)[0],candidates.get(i)[1]});
			}else if(empty==max2) {
				candidates2.add(new int[] {candidates.get(i)[0],candidates.get(i)[1]});
			}
		}
		
		// 2번조건에 맞는 칸이 몇개인지 확인
		if(candidates2.size()==1) { //1개인경우 그 칸이 학생자리
			map[candidates2.get(0)[0]][candidates2.get(0)[1]] = shark;
			return;
		}
		
		// 2번조건에 맞는 칸이 여러개인 경우 3번 조건. 행번호를 확인
		List<int[]> candidates3 = new ArrayList<>();
		int min =N;
		for (int i = 0; i < candidates2.size(); i++) {
			int row = candidates2.get(i)[0];
			if(row<min) {
				min = row;
				candidates3.clear();
				candidates3.add(new int[] {candidates2.get(i)[0],candidates2.get(i)[1]});
			}else if(row==min) {
				candidates3.add(new int[] {candidates2.get(i)[0],candidates2.get(i)[1]});
			}
		}
		// 3번조건에 맞는 칸이 몇개인지 확인
		if(candidates3.size()==1) { //1개인경우 그 칸이 학생자리
			map[candidates3.get(0)[0]][candidates3.get(0)[1]] = shark;
			return;
		}
		
		// 3번조건에 맞는 칸이 여러개인 경우 4번 조건. 열번호를 확인
		List<int[]> candidates4 = new ArrayList<>();
		int min2 =N;
		for (int i = 0; i < candidates3.size(); i++) {
			int col = candidates3.get(i)[1];
			if(col<min2) {
				min2 = col;
				candidates4.clear();
				candidates4.add(new int[] {candidates3.get(i)[0],candidates3.get(i)[1]});
			}else if(col==min2) {
				candidates4.add(new int[] {candidates3.get(i)[0],candidates3.get(i)[1]});
			}
		}
		
		// 3번조건에 맞는 칸이 몇개인지 확인
		if(candidates4.size()==1) { //1개인경우 그 칸이 학생자리
			map[candidates4.get(0)[0]][candidates4.get(0)[1]] = shark;
			return;
		}
	}
}
