import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1916_최소비용 {

	static ArrayList<ArrayList<int[]>> adj;
	static boolean[] isVisited;
	static int[] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		isVisited = new boolean[N+1];
		adj = new ArrayList<>();
		dist = new int[N+1];
		for(int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine()," ");
			adj.get(Integer.parseInt(st.nextToken()))
				.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			
		}
		st = new StringTokenizer(br.readLine()," ");
		
		System.out.println(dijk(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		
	}
	
	private static int dijk(int start, int end) {
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<int[]> pq= new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);
		pq.offer(new int[] {0, start});
		isVisited[start] = true;
		
		while(!pq.isEmpty()) {
			
			int num = pq.poll()[1];
			Iterator<int[]> it = adj.get(num).iterator();
			while(it.hasNext()) {
				
				int[] next = it.next();
				
				if(!isVisited[next[0]] && dist[next[0]]>dist[num]+next[1]) {
					dist[next[0]] = dist[num]+next[1];
					pq.offer(new int[] {dist[next[0]], next[0]});
				}
			}
		}
		return dist[end];	
	}
  
}
