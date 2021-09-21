import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
    static int N,M,map[][],score;
    static boolean[][] vst,vstRainbow;
    static List<int[]> removeList;
    static class BlockGroup {
    	int[] origin;
    	int size;
    	int rainbowCnt;
    	BlockGroup(int[] origin, int size ,int rainbowCnt){
    		this.origin = origin;
    		this.size = size;
    		this.rainbowCnt = rainbowCnt;
    	}
    }
    static List<BlockGroup> list;
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        //autoplay
        while(true) {   
        	list = new ArrayList<>();
        	findLargestBlockGroup();
        	if(list.size()==0) break;
        	if(SelectBlockGroup()==1) break;
        	applyGravity();
        	rotateCounterClock90();
        	applyGravity();
        }
        System.out.println(score);
    }

	private static void rotateCounterClock90() {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = copy[j][N - 1 - i];
			}
		}
	}

	private static void applyGravity() {
		for (int j = 0; j < N; j++) { //열 별로 적용
			for (int i = N-1; i >= 0 ; i--) { // 아래 행부터 적용
				if(map[i][j]==M+1 ||map[i][j]==-1) continue; //빈칸이거나 검은색블록이면 넘어감
				//빈칸 아니면 -1이나 맨 아래나  다른 블록 만날때까지 밑으로 쭉 내림
				int temp = i;
				while(temp<N-1 && map[temp+1][j]==M+1) temp++;

				if(temp!=i) {
					
					map[temp][j] = map[i][j];
					map[i][j] = M+1;
				}
			}
		}
		
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("============");
	}

	private static int SelectBlockGroup() {
    	int max=0;
    	List<BlockGroup> candidates = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
    		int size = list.get(i).size;
    		if(size>max) {
    			max = size;
    			
    			candidates.clear();
    			candidates.add(list.get(i));
    		}else if(size==max) {
    			candidates.add(list.get(i));
    		}
		}
		if(max==1) return max;
		if(candidates.size()==1) removeBlocks(candidates.get(0));
		else {
			int max2 = 0;
			List<BlockGroup> candidates2 = new ArrayList<>();
			for (int i = 0; i < candidates.size(); i++) {
	    		int rainbowCnt = candidates.get(i).rainbowCnt;
	    		if(rainbowCnt>max2) {
	    			max2 = rainbowCnt;
	    			candidates2.clear();
	    			candidates2.add(candidates.get(i));
	    		}else if(rainbowCnt==max2) {
	    			candidates2.add(candidates.get(i));
	    		}
	    	}
			
			if(candidates2.size()==1) removeBlocks(candidates2.get(0));
			else {
				int maxRow = 0;
				List<BlockGroup> candidates3 = new ArrayList<>();
				for (int i = 0; i < candidates2.size(); i++) {
		    		int r = candidates2.get(i).origin[0];
		    		if(r>maxRow) {
		    			maxRow = r;
		    			candidates3.clear();
		    			candidates3.add(candidates2.get(i));
		    		}else if(r==maxRow) {
		    			candidates3.add(candidates2.get(i));
		    		}
		    	}
				if(candidates3.size()==1) removeBlocks(candidates3.get(0));
				else {
					int maxCol = 0;
					List<BlockGroup> candidates4 = new ArrayList<>();
					for (int i = 0; i < candidates3.size(); i++) {
			    		int c = candidates3.get(i).origin[1];
			    		if(c>maxCol) {
			    			maxRow = c;
			    			candidates4.clear();
			    			candidates4.add(candidates3.get(i));
			    		}else if(c==maxCol) {
			    			candidates4.add(candidates3.get(i));
			    		}
			    	}
					removeBlocks(candidates4.get(0));
				}
					
			}
		}
		return 0;
	}

	private static void removeBlocks(BlockGroup blockGroup) {
		int[] origin = blockGroup.origin;
		removeList = new ArrayList<>();
		vst = new boolean[N][N];
		vstRainbow = new boolean[N][N];
		dfs(origin[0],origin[1], map[origin[0]][origin[1]], false,true);
		for (int i = 0; i < removeList.size(); i++) {
			int r = removeList.get(i)[0];
			int c = removeList.get(i)[1];
			map[r][c] = M+1;
		}
		score+= removeList.size()*removeList.size();
	}


	private static void findLargestBlockGroup() {
		vst = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0 && map[i][j]<=M && !vst[i][j]) {
					vstRainbow = new boolean[N][N]; // 무지개 방문 초기화
					list.add(new BlockGroup(new int[] {i,j}, dfs(i,j,map[i][j],false,false), countRainbow()));
				}
			}
		}
		
	}
	private static int countRainbow() {
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(vstRainbow[i][j]) cnt++;
			}
		}
		return cnt;
	}
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	private static int dfs(int i, int j,int num,boolean isRainbow, boolean isRemoveMode) {
		if(isRemoveMode) removeList.add(new int[] {i,j});
			
		if(!isRainbow) vst[i][j] = true;
		else vstRainbow[i][j] = true;
		
		int result=0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr<0||nc<0||nr==N||nc==N ||map[nr][nc]==-1) continue;
			else if(map[nr][nc]==num) {
				if(!vst[nr][nc]) result += dfs(nr,nc,num,false,isRemoveMode);
				else continue;
			} else if(map[nr][nc]==0) {
				if(!vstRainbow[nr][nc]) result += dfs(nr,nc,num,true,isRemoveMode);
				else continue;
			}
		}
		return 1+result;
	}
  
	
}
