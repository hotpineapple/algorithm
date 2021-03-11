import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Boj_11724_연결요소의개수 {

	static int N,M,cnt;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N+1];
		adj = new ArrayList<>();
		for(int i=0;i<=N;i++) adj.add(new ArrayList<>());
    
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		for(int i=1;i<=N;i++){
			if(!isVisited[i]) {
				cnt++;
				dfs(i);	
			}
		}
		System.out.println(cnt);
	}
	
	private static void dfs(int curr) {
//		System.out.println("curr: "+curr[0]+", "+curr[1]);
		isVisited[curr] = true;
		Iterator<Integer> it = adj.get(curr).iterator();
		while(it.hasNext()) {
			int next = it.next();
			if(!isVisited[next]) dfs(next);
		}
	}
	
}
