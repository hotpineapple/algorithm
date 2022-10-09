package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M, map[][], max;
 	public static void main(String[] args) throws IOException {
 		// 입력
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st = new StringTokenizer(br.readLine());
 		
 		N = Integer.parseInt(st.nextToken());
 		M = Integer.parseInt(st.nextToken());
 		
 		map = new int[N][M];
 		for(int i=0;i<N;i++) {
 			st = new StringTokenizer(br.readLine());
 			for(int j=0;j<M;j++) {
 				map[i][j] = Integer.parseInt(st.nextToken());
 			}
 		}
 		
 		// 처리
 		putTetromino1();
 		putTetromino2();
 		putTetromino3();
 		putTetromino4();
 		putTetromino5();
 		System.out.println(max);
	}
	private static void putTetromino5() {
		for(int i=0;i<N-2;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i+1][j]+ map[i+2][j]);
			}
		}
		for(int i=0;i<N-2;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i+1][j+1]+ map[i+2][j+1]);
			}
		}
		for(int i=0;i<N-2;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+2][j]+ map[i+2][j+1]);
			}
		}
		for(int i=0;i<N-2;i++) {
			for(int j=1;j<M;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+2][j]+ map[i+2][j-1]);
			}
		}
		for(int i=1;i<N;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i][j+2]+ map[i-1][j+2]);
			}
		}
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i][j+2]+ map[i+1][j+2]);
			}
		}
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i][j+2]+ map[i+1][j]);
			}
		}
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+1][j+1]+ map[i+1][j+2]);
			}
		}
	}
	private static void putTetromino4() {
		for(int i=1;i<N;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i-1][j+1]+ map[i][j+2]);
			}
		}
		for(int i=1;i<N-1;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i-1][j+1]+ map[i+1][j+1]);
			}
		}
		for(int i=0;i<N-2;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+1][j+1]+ map[i+2][j]);
			}
		}
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i+1][j+1]+ map[i][j+2]);
			}
		}
		
	}
	private static void putTetromino3() {
		for(int i=0;i<N-2;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+1][j+1]+ map[i+2][j+1]);
			}
		}
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i+1][j+1]+ map[i+1][j+2]);
			}
		}
		for(int i=1;i<N;i++) {
			for(int j=0;j<M-2;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i-1][j+1]+ map[i-1][j+2]);
			}
		}
		for(int i=2;i<N;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i-1][j]+ map[i-1][j+1]+ map[i-2][j+1]);
			}
		}
		
	}
	private static void putTetromino2() {
		// 가로로 4개
		for(int i=0;i<N;i++) {
			for(int j=0;j<M-3;j++) {
				max = Math.max(max, map[i][j]+ map[i][j+1]+ map[i][j+2]+ map[i][j+3]);
			}
		}
		// 세로로 4개
		for(int i=0;i<N-3;i++) {
			for(int j=0;j<M;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i+2][j]+ map[i+3][j]);
			}
		}
	}
	private static void putTetromino1() {
		// 정사각형
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<M-1;j++) {
				max = Math.max(max, map[i][j]+ map[i+1][j]+ map[i][j+1]+ map[i+1][j+1]);
			}
		}
		
	}
 }
