/*크루스칼*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링2 {

	static int min = Integer.MAX_VALUE,N, nums[], area1[], area2[];
	static boolean[] isSelected;
	static boolean[][] adj;
	
	static int[] parents1, parents2;
	static void make() {
		for (int i = 0; i < N; i++) {
			parents1[area1[i]] = area1[i];
			parents2[area2[i]] = area2[i];
		}
	}
	static int findSet(int no, int a) {
		if(no==1) {
			if(parents1[a]==a) return a;
			return parents1[a] = findSet(no, parents1[a]);
		}else {
			if(parents2[a]==a) return a;
			return parents2[a] = findSet(no, parents2[a]);
		}
	}
	static boolean union(int no, int a, int b) {
		if(no ==1) {
			int aRoot = findSet(no,a);
			int bRoot = findSet(no,b);
			if(aRoot == bRoot) return false;
			
			parents1[bRoot] = aRoot;

			return true;
		}else {
			int aRoot = findSet(no,a);
			int bRoot = findSet(no,b);
			if(aRoot == bRoot) return false;
			
			parents2[bRoot] = aRoot;

			return true;
		}	
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //구역 개수
	
		nums = new int[N+1];
		isSelected = new boolean[N+1];
		adj = new boolean[N+1][N+1];
		Arrays.fill(adj[0], true);
		for (int i = 0; i <= N; i++) adj[i][0]= true;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			int from = i;
			st = new StringTokenizer(br.readLine()," ");
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int to = Integer.parseInt(st.nextToken());
				adj[to][from] = adj[from][to] = true;
			}
		}
		
		// 가능한 경우의 수로 나누기 (부분집합)
		subsets(1);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
		
	}
	private static void subsets(int cnt) {
		if(cnt == N+1) {
			area1 = new int[N];
			area2 = new int[N];
			int cnt1=0,cnt2=0;
			for(int i = 1; i<=N; i++) {
				if(isSelected[i] == true) area1[cnt1++] = i;
				else area2[cnt2++] = i;
			}
			
			
			if(cnt1==0 || cnt2==0) return;
//			System.out.println("[area1]"+Arrays.toString(area1));
//			System.out.println("[area2]"+Arrays.toString(area2));
			span();
			if(isConnected()) min = Math.min(getDiff(), min);
			return;
		}
		
		isSelected[cnt] = true;
		subsets(cnt+1);
		isSelected[cnt] = false;
		subsets(cnt+1);	
		
	}
	
	private static void span() {
		parents1 = new int[N+1];
		parents2 = new int[N+1];
		make();
		
		//area1
		for(int i=0;i<N-1;i++) {
			if(area1[i]==0) continue;
			for(int j=1;j<N;j++) {
				if(area1[j]==0) continue;
				if(adj[area1[i]][area1[j]]) union(1,area1[i],area1[j]);
			}
		}
		
		//area2
		for(int i=0;i<N-1;i++) {
			if(area2[i]==0) continue;
			for(int j=1;j<N;j++) {
				if(area2[j]==0) continue;
				if(adj[area2[i]][area2[j]]) union(2,area2[i],area2[j]);
			}
		}
		
//		System.out.println("Area 1 union : "+Arrays.toString(parents1));
//		System.out.println("Area 2 union : "+Arrays.toString(parents2));
	}
	private static boolean isConnected() { // 각 선거구가 연결되어잇는지 확인하는 메서드
		
		//area1
		for (int i = 0; i < N-1; i++) {
			if(area1[i]==0) continue;
			for (int j = i+1; j < N; j++) {
				if(area1[j]==0) continue;
				if(findSet(1,area1[i])!=findSet(1,area1[j])) {
//					System.out.println("area1 not connected");
					return false;
				}
			}
		}
		
		//area2
		for (int i = 0; i < N-1; i++) {
			if(area2[i]==0) continue;
			for (int j = i+1; j < N; j++) {
				if(area2[j]==0) continue;
				if(findSet(2,area2[i])!=findSet(2,area2[j])) {
//					System.out.println("area2 not connected");
					return false;
				}
			}
		}

		return true;
	}
	
	private static int getDiff() { //두 선거구의 인구 차이 구하는 메서드
		int sum1=0,sum2=0;
		
		for (int i = 0; i < N; i++) sum1+=nums[area1[i]];
		for (int i = 0; i < N; i++) sum2+=nums[area2[i]];

		return Math.abs(sum1-sum2);
	}

}
