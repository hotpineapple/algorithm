import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {
	
	static class Edge implements Comparable<Edge>{		
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}		
	}
	
	static int N;
	static double E;
	static int[] parents;
	static int[] rank;
	static Edge[] edgeList;
	
	static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		if(rank[aRoot] > rank[bRoot]) {
			parents[bRoot] = aRoot;
		}else {
			parents[aRoot] = bRoot;
			if(rank[aRoot] == rank[bRoot]) rank[bRoot]++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			parents = new int[N];
			rank = new int[N];
			edgeList = new Edge[N*N-N];
			int[] xPos = new int[N];
			int[] yPos = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				xPos[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				yPos[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i != j) {
						double dist = Math.sqrt(Math.pow(Math.abs(xPos[i]-xPos[j]), 2) + (Math.pow(Math.abs(yPos[i]-yPos[j]), 2)));
						edgeList[cnt++] = new Edge(i,j,Math.pow(dist,2)*E);
					}
				}
			}	
			//1. 간선리스트를 가중치 기준 오름차순 정렬
			Arrays.sort(edgeList);
			
			make();
			double result = 0;
			int count = 0; //선택한 간선수
			
			for (Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++count == N-1) break;
				}
			}
		
			// ============= 출력 ==============
			sb.append("#").append(tc).append(" ").append((int)result).append("\n");

		}
		System.out.println(sb.toString());
	}
}

