import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1753_최단경로2_1 {

	static Long[] dist;
	static boolean[] isVisited;
	static ArrayList<ArrayList<int[]>> adj;
	static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		adj = new ArrayList<>();
		for(int i = 0; i <= V; i++) adj.add(new ArrayList<>());
		isVisited = new boolean[V+1];
		dist = new Long[V+1];
		Arrays.fill(dist, INF);
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			adj.get(Integer.parseInt(st.nextToken()))
				.add(new int[] {Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())});
		} 
		dijk(K);
		
		for (int i = 1; i <= V; i++) {
			System.out.println(dist[i]==INF ? "INF" : dist[i]);
		}
	}
	
	private static void dijk(int start) {
		
		PriorityQueue<long[]> pq = new PriorityQueue<>((o1,o2)->(int)(o1[0]-o2[0]));
		dist[start]=0L;
		pq.offer(new long[] {0,start});
		
		while(!pq.isEmpty()) {
			int num = (int)pq.poll()[1];
			
			//========= 수정 ==========
			if(isVisited[num]) continue;
			//=========================
      
			isVisited[num] = true;
			Iterator<int[]> it = adj.get(num).iterator();
			
			while(it.hasNext()) {
				int[] next = it.next();
				if(!isVisited[next[0]] && dist[next[0]] > dist[num] + next[1]) {
					dist[next[0]] = dist[num] + next[1];
					pq.offer(new long[] {dist[next[0]], next[0]});
				}
			}
		}
	}

}
