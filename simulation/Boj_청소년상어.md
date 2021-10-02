# 청소년상어

#### 어려웠던 점
* 방향 바꿔서 이동시 기본 방향 바뀌는 것 캐치못함
* 맵 상태 저장.복원 로직
* 지역변수/전역변수 범위 인지하지 못하고 코딩
* 참조변수와 일반변수 차이점 인지하지 못하고 코딩

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	static int ans;
	static Fish[][] map;
	static class Fish {
		int r;
		int c;
		int num; //1~16
		int d; // 위 부터 반시계방향으로 증가

		Fish(int r, int c, int num, int d) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.d = d;
		}
		Fish(int num, int d) {
			this.num = num;
			this.d = d;
		}
	}
 
    public static void main(String[] args) throws NumberFormatException, IOException {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	map = new Fish[4][4];
    	
    	for (int i = 0; i < 4; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = new Fish(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1);
			}
		}
//    	System.out.println("init:");
//    	print();
    	int cnt = 0;
    	
    	// 상어가 0,0 물고기 먹고 그 칸으로 이동
    	Fish f0 = map[0][0];
    	cnt += f0.num;
    	Fish teenShark = new Fish(0,0,99,f0.d);
    	map[0][0] = teenShark;
    	
//    	print();
    	// 시작
    	go(teenShark, cnt);
    	
    	System.out.println(ans);
	}
    
	private static void go(Fish teenShark, int cnt) {
		ans = Math.max(cnt, ans);
//		System.out.println("먹은 물고기 번호 합:"+ans);
		
		int r = teenShark.r;
		int c = teenShark.c;
		int d = teenShark.d;
		
		Fish[][] map2 = new Fish[4][4];
		Fish[][] map3 = new Fish[4][4];
		
		save(map2); // 물고기이동 전 map 상태 저장 - 리턴용	
		fishMove();	
		save2(map3); // 물고기이동 후 map 상태 저장 - 상어 이동용
		
		for(int i = 1; i < 4; i++) {
			load2(map3);
			
			int nr = r + dr[d]*i;
			int nc = c + dc[d]*i;
			
			if(nr<0||nc<0||nr>=4||nc>=4) break;
			if(map[nr][nc].num == 0) continue;
			
			// 먹기
			map[r][c] = new Fish(r,c,0,-1);	
			int targetNum = map[nr][nc].num;
			int targetDir = map[nr][nc].d;
	    	map[nr][nc] = new Fish(nr,nc,99,targetDir);
//	    	System.out.println("shark move:");
//	    	print();
	    	
	    	go(new Fish(nr,nc,99,targetDir), cnt + targetNum);

//	    	print();
		}
		load(map2);
	}
	

	private static void load(Fish[][] map2) {
//		System.out.println("물고기이동전 상태로 복원");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = map2[i][j];
			}
		}
//		print();
		
	}

	private static void save2(Fish[][] map3) {
//		System.out.println("물고기 이동  후 상태 저장");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map3[i][j] = map[i][j];
			}
		}
//		print();
	}

	private static void save(Fish[][] map2) {
//		System.out.println("물고기 이동  전 상태 저장");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map2[i][j] = map[i][j];
			}
		}
//		print();
	}
	private static void load2(Fish[][] map3) {
//		System.out.println("물고기이동후 상태로 복원");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = map3[i][j];
			}
		}
//		print();
	}
	private static void print() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(map[i][j].num+" ");
			}
			System.out.println();
		}
		System.out.println("======================");
	}

	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,-1,-1,-1,0,1,1,1};
	
	private static void fishMove() {
		
		for (int n = 1; n <= 16; n++) { // 작은 번호부터 이동	
			outer:for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(map[i][j].num == n) {

						Fish f = map[i][j];
						int dir = f.d;
						int cnt=0;
						for (int d = dir; d < 8; d=(d+1)%8) {
							if(cnt==8) break outer;
							cnt++;
							
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							if(nr<0||nc<0||nr==4||nc==4) continue;
							if(map[nr][nc].num == 99) continue;
							
							Fish nf = map[nr][nc];
							map[nr][nc] = new Fish(i,j,n,d);
							map[i][j] = nf;
							
							break outer;
						}
					}
				}
			}
		}
		
//		System.out.println("fish move:");
//		print();
	}

}
