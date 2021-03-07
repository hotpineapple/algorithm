/*부모와 자식 사이 = 1촌
 * 나와 아버지 = 1촌
 * 아버지와 할아버지 = 1촌
 * 아버지와 아버지 형제 = 2촌
 * 나와 아버지 형제 = 3촌*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2644_촌수계산 {

	static int N, M;
	
	static class Graph{
		
		int gN;
		ArrayList<ArrayList<Integer>> adj;
		boolean[] isVisited;
		
		Graph(){}
		Graph(int n){
			gN = n;
			adj = new ArrayList<>();
			for(int i=0;i<=n;i++) adj.add(new ArrayList<Integer>());
			isVisited = new boolean[n];
		}
		
		void sortList() {
			for(int i=0;i<gN;i++) Collections.sort(adj.get(i));
		}
		
		void addEdge(int u, int v) {
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		int bfs(int a, int b) {
			
			Queue<Integer> q = new LinkedList<>();
			
			q.offer(a);
			isVisited[a] = true;
			int level=0;
			while(!q.isEmpty()) {
				
				int size = q.size();
				for(int i=0;i<size;i++) {
					
					int curr = q.poll();
					if(curr==b) return level;
					Iterator<Integer> it = adj.get(curr).iterator();
					while(it.hasNext()) {
						int next = it.next();
						if(!isVisited[next]) {
							isVisited[next] = true;
							q.offer(next);
						}
					}
				}
				level++;
			}
		
			return -1;
		
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 사람 수
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int a = Integer.parseInt(st.nextToken()); // 관계 구할 사람 1의 번호
		int b = Integer.parseInt(st.nextToken()); // 관계 구할 사람 2의 번호
		M = Integer.parseInt(br.readLine()); // 부모-자식 관계의 개수
		Graph g = new Graph(N+1);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			g.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(g.bfs(a,b));
	}

}
