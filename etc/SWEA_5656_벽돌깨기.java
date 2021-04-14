import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	
	static int N, W, H, min, input[], output[], map[][], map2[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= TC; tc++) {
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken()); // 구슬 떨어뜨리는 횟수		
			W = Integer.parseInt(st.nextToken()); // 가로길이			
			H = Integer.parseInt(st.nextToken()); // 세로길이	
			input = new int[W];
			output = new int[N];
			for(int i=0;i<W;i++) input[i] = i;

			map = new int[H][W];
			map2 = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < W; j++) {
					map2[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 구슬 떨어뜨릴 위치 고르기 (중복순열)
			p(0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void p(int cnt) {
		
		if(cnt == N) {
			copy();
			play(output);
			return;
		}
		
		for(int i=0; i<W ;i++) {
			output[cnt] = input[i];
			p(cnt+1);
		}
	}

	private static void copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = map2[i][j];
			}
		}
		
	}

	private static void play(int[] arr) {
		for (int i = 0; i < N; i++) shoot(arr[i]);
		
		min = Math.min(min, countBlock());
	}

	private static int countBlock() {
		int res = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] != 0) res++;
			}
		}
		return res;
	}

	private static void shoot(int c) {
//		System.out.println("shoot "+ c +"th col :");
		for (int r = 0; r < H; r++) {
			if(map[r][c] != 0) {
				bomb(r,c);
				down();
        
				break;
			}		
		}	
	}

	private static void down() {
		//위부터 탐색하면서  0이면 swap한다.
		while(true) {
			boolean flag = false;
			for (int c = 0; c < W; c++) {
				for (int r = 1; r < H; r++) {
					if(map[r][c] == 0 && map[r-1][c] != 0) {
						map[r][c] = map[r-1][c];
						map[r-1][c] = 0;
						flag = true;
					}
				}
				
			}
			if(!flag) break;
		}
		
	}

	private static void bomb(int r, int c) {
//		System.out.println("bomb starts at "+r+","+c+" with power="+map[r][c]);
		int power = map[r][c];
		map[r][c] = 0;
		if(power <= 1) return;
		
		//아래방향
		int limit = Math.min(r+power, H);
		for(int i=r+1; i < limit ;i++) bomb(i,c);
		
		//위방향
		limit = Math.max(r-power+1, 0);
		for(int i=r-1; i >= limit ;i--) bomb(i,c);
		
		//왼쪽방향
		limit = Math.max(c-power+1, 0);
		for(int i=c-1; i >= limit ;i--) bomb(r,i);
		
		//오른쪽방향
		limit = Math.min(c+power, W);
		for(int i=c+1; i < limit ;i++) bomb(r,i);
	
	}
	private static void print() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
