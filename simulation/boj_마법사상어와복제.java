import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M,S,sharkR,sharkC;
	static int[][][] map = new int[5][5][9];
	static int[][][] map2 = new int[5][5][9];
	static int[][][] map3 = new int[5][5][9];
	static int[][] smell = new int[5][5];

 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		
 		M = Integer.parseInt(st.nextToken());
 		S = Integer.parseInt(st.nextToken());
		
 		for(int i=0;i<M;i++) {
 			 st = new StringTokenizer(br.readLine());
 			 int r = Integer.parseInt(st.nextToken());
 			 int c = Integer.parseInt(st.nextToken());
 			 int d = Integer.parseInt(st.nextToken());
 			 map[r][c][d]++; // 물고기
// 			 fishes.add(new Fish(r,c,d));
 		}
 		
 		st = new StringTokenizer(br.readLine());
 		sharkR = Integer.parseInt(st.nextToken());
		sharkC = Integer.parseInt(st.nextToken());

 		// 처리
 		for(int tc=0;tc<S;tc++) {
 			init();
// 			System.out.println("init");
// 			print();
// 			printSmell();
 			copyToFish();
// 			System.out.println("!");
 			moveFish();
// 			print();
// 			System.out.println("!!");
 			moveShark();
// 			print();
// 			System.out.println("!!!");
 			removeSmell();
// 			System.out.println("!!!!");
 			copyFromFish();
// 			System.out.println("!!!!");
// 			print();
 		}
// 		System.out.println("?");
 		// 출력
 		System.out.println(getFish());
	}
 	private static void printSmell() {
 		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				System.out.print(smell[i][j]+" ");
			}
			System.out.println();
 		}
	}
	private static void init() {
 		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				for(int k=1;k<=8;k++) map3[i][j][k] = 0;
					
			}
		}
	}
	private static void print() {
 		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				int sum=0;
				for(int k=1;k<=8;k++) {
					sum+=map[i][j][k];
				}
				System.out.print(sum+" ");
			}
			System.out.println();
		}
 		System.out.println("===========");
	}
	private static int getFish() {
 		int sum=0;
 		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				for(int k=1;k<=8;k++) {
					sum+=map[i][j][k];
				}
			}
		}
// 		System.out.println(sum);
 		return sum;
	}
	private static void copyToFish() {
		map2 = new int[5][5][9];
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				for(int k=1;k<=8;k++) {
					map2[i][j][k] = map[i][j][k];
				}
			}
		}
	}
	private static void copyFromFish() {
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				for(int k=1;k<=8;k++) {
					map[i][j][k] += map2[i][j][k];
				}
			}
		}
	}
	private static void removeSmell() {
 		for(int i=1;i<=4;i++) {
 			for(int j=1;j<=4;j++) {
 				smell[i][j]--;
 			}
 		}
	}
 	private static void moveShark() {
// 		System.out.println("Shark at "+shark.r+","+shark.c);
 		int[] maxDir = null;
 		int max=0;
 		for(int i=3;i>=0;i--) {
 			for(int j=3;j>=0;j--) {
 				for(int k=3;k>=0;k--) {
 					int res = checkFish(i,j,k);
	 				if(res==-1) continue;
	 				else if(max<= res) {
	 					max = res;
	 					maxDir = new int[] {i,j,k};
	 				}
 		 		}
 	 		}
 		}
		
// 		if(maxDir == null) return;
 		
 		// 움직이기 & 물고기 제외
 		moveSharkReal(maxDir[0],maxDir[1],maxDir[2]);
	}
	
	private static void moveSharkReal(int i, int j, int k) {
//		System.out.println(i+","+j+","+k);
		int r = sharkR;
		int c = sharkC;
		
//		for(int m=1;m<=8;m++) {
//			if(map[r][c][m]>0) {
//				map[r][c][m]=0;
//				smell[r][c] = 3;
//			}
//		}
		
		for(int m=1;m<=8;m++) {
			int nr1 = r + dr2[i];
			int nc1 = c + dc2[i];
			if(map[nr1][nc1][m]>0) {
				map[nr1][nc1][m]=0;
//				for(int m=0;k<map[nr1][nc1].fishes.size();k++) fishes.remove(map[nr1][nc1].fishes.get(m));
				smell[nr1][nc1] = 3;
			}
			
			int nr2 = nr1 + dr2[j];
			int nc2 = nc1 + dc2[j];
			if(map[nr2][nc2][m]>0) {
				map[nr2][nc2][m]=0;
//				for(int m=0;k<map[nr2][nc2].fishes.size();k++) fishes.remove(map[nr2][nc2].fishes.get(m));
				smell[nr2][nc2] = 3;
			}
			
			int nr3 = nr2 + dr2[k];
			int nc3 = nc2 + dc2[k];
			if(map[nr3][nc3][m]>0) {
				map[nr3][nc3][m]=0;
//				for(int m=0;k<map[nr3][nc3].fishes.size();k++) fishes.remove(map[nr3][nc3].fishes.get(m));
				smell[nr3][nc3] = 3;
			}
			
			sharkR = nr3;
			sharkC = nc3;

		}
		
	}
	static int[] dc2 = {0,-1,0,1};
	static int[] dr2 = {-1,0,1,0};
	private static int checkFish(int i, int j, int k) {
		int r = sharkR;
		int c = sharkC;
		
		int nr1 = r + dr2[i];
		int nc1 = c + dc2[i];
		int nr2 = nr1 + dr2[j];
		int nc2 = nc1 + dc2[j];
		int nr3 = nr2 + dr2[k];
		int nc3 = nc2 + dc2[k];
		
//		System.out.println(i+","+j+","+k+": ("+nr1+","+nc1+"),("+nr2+","+nc2+"),("+nr3+","+nc3+")");
		if(nr1<=0||nc1<=0||nr2<=0||nc2<=0||nr3<=0||nc3<=0) return -1;
		if(nr1>4||nc1>4||nr2>4||nc2>4||nr3>4||nc3>4) return -1;
		
		int cnt=0;
		for(int m=1;m<=8;m++) cnt+=map[nr1][nc1][m];
		if(nr2!=nr1||nc2!=nc1) for(int m=1;m<=8;m++) cnt+=map[nr2][nc2][m];
		if((nr3!=nr2||nc3!=nc2)&&(nr3!=nr1||nc3!=nc1)) for(int m=1;m<=8;m++) cnt+=map[nr3][nc3][m];
		
//		System.out.println(i+","+j+","+k+": "+cnt);
		return cnt;
	}
	static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dc = {0,-1,-1,0,1,1,1,0,-1};
	private static void moveFish() {
//		System.out.println("fish:"+fishes.size());
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				OUTER:for(int k=1;k<=8;k++) {
					if(map[i][j][k]==0) continue;
					int d = k;
					while(!isFishMovable(i, j, d)) {
						d--;
						if(d==0) d=8;
						if(k==d) {
							map3[i][j][k] += map[i][j][k];
							continue OUTER; // 어떤 방향으로도 이동할 수 없는 경우
						}
					} 

					int nr = i + dr[d];
					int nc = j + dc[d];
//					System.out.println("fish move from ("+i+","+j+") to ("+nr+","+nc+") w/ dir"+d);
					map3[nr][nc][d]+=map[i][j][k];
//					map3[i][j][k]=0;
					
					
//					f.r = nr;
//					f.c = nc;
//					f.dir =d;
				}
			}
		}
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				for(int k=1;k<=8;k++) {
					map[i][j][k] = map3[i][j][k];
				}
			}
		}
		
	}
	private static boolean isFishMovable(int r, int c, int d) {
		int nr = r + dr[d];
		int nc = c + dc[d];

		if(nr<=0||nr>4||nc<=0||nc>4) {
//			System.out.println("range over");
			return false;
		}
		if(nr==sharkR && nc==sharkC) {
//			System.out.println("shark here");
			return false;
		}
		if(smell[nr][nc]>0) {
//			System.out.println("dead fish smell");
			return false;
		}
		
//		System.out.println(r+","+c+","+d+" is movable");
		return true;
	}
 }
