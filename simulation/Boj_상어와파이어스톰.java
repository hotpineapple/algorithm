import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
	static int N,sqr,Q,ans,arr[];
	static Cell map[][];
	static boolean vst[][];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static class Cell {
		boolean isTarget;
		int ice;
		Cell(int ice){
			this.ice = ice;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	sqr = (int) Math.pow(2, N);
    	map = new Cell[sqr][sqr];
    	vst = new boolean[sqr][sqr];
    	for (int i = 0; i < sqr; i++) {
    		st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < sqr; j++) {
				map[i][j] = new Cell(Integer.parseInt(st.nextToken())); 
			}
		}
    	
    	arr = new int[Q];
    
    	st = new StringTokenizer(br.readLine()," ");
    	for (int i = 0; i < Q; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
		}
    	
    	for (int i = 0; i < Q; i++) {
			fireStorm(arr[i]);
		}
    	int ans1 = count();
    	System.out.println(ans1);
    	int num = -1;
    	for (int i = 0; i < sqr; i++) {			
    		for (int j = 0; j < sqr; j++) {
    			if(!vst[i][j] && map[i][j].ice>0) {
    				dfs(i,j,num);
    				ans = Math.max(count2(num), ans);
    				num--;
//    				print();
    			}
    			
    		}
		}
    	System.out.println(ans);
    }
	private static void dfs(int i, int j,int num) {
		
		vst[i][j] = true;
		map[i][j].ice = num;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr<0||nc<0||nr==sqr||nc==sqr) continue;
			if(map[nr][nc].ice==0 || vst[nr][nc]) continue;
			
			dfs(nr,nc,num);
		}
	}
	private static int count() {
		int sum=0;
		for (int i = 0; i < sqr; i++) {
			for (int j = 0; j < sqr; j++) {
				sum += map[i][j].ice;
			}
		}
		return sum;
	}
	private static int count2(int num) {
		int sum=0;
		for (int i = 0; i < sqr; i++) {
			for (int j = 0; j < sqr; j++) {
				if(map[i][j].ice == num)sum++;
			}
		}
		return sum;
	}
	private static void fireStorm(int level) {
		int len = (int) Math.pow(2, level);

		for (int i = 0; i < sqr; i+=len) {
			for (int j = 0; j < sqr; j+=len) {
				rotate(i,j,len);
			}
		}
		
		for (int i = 0; i < sqr; i++) {
			for (int j = 0; j < sqr; j++) {
				int cnt=0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr<0||nc<0||nr==sqr||nc==sqr) continue;
					if(map[nr][nc].ice==0) continue;
					cnt++;
				}
				if(cnt<3) map[i][j].isTarget = true;
			}
		}

		for (int i = 0; i < sqr; i++) {
			for (int j = 0; j < sqr; j++) {
				if(map[i][j].isTarget) {
					if(map[i][j].ice>0)map[i][j].ice--;
					map[i][j].isTarget = false;
				}
			}
		}
//		print();
	}
	private static void print() {
		for (int i = 0; i < sqr; i++) {
			for (int j = 0; j < sqr; j++) {
				System.out.print(map[i][j].ice+" ");
			}
			System.out.println();
		}
		System.out.println("=======");
		
	}
	private static void rotate(int i, int j, int len) {
	
		Cell[][] copy = new Cell[len][len];
		for (int k = 0; k < len; k++) {
			for (int k2 = 0; k2 < len; k2++) {
				copy[k][k2] = map[i+k][j+k2];
			}
		}
		for (int k = 0; k < len; k++) {
			for (int k2 = 0; k2 < len; k2++) {
				map[i+k][j+k2] = copy[len-1-k2][k];
			}
		}
		
	}
    
    
}
