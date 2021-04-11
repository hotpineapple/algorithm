import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1251_하나로_Prim {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 총 환경 부담금을 최소로 하여 N개의 섬이 모두 연결하기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = 0;
		int[][] pos = null; // 각 섬의 x,y 위치
		StringTokenizer st = null;
		double[][] adj = null; //인접행렬
		boolean[] isVisited = null; // 싸이클 발생하지 않도록 방문 관리
		double[] minEdge = null; // 각 정점이 (신장트리에 연결된 정점과) 연결된 간선 중 최소비용
		
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			pos = new int[N][2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) pos[i][0] = Integer.parseInt(st.nextToken()); // 각 섬의 x좌표
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) pos[i][1] = Integer.parseInt(st.nextToken()); // 각 섬의 y좌표

			double E = Double.parseDouble(br.readLine());
			
			// 모든 섬이 서로 연결될 수 있으며 그 중 최소비용을 구해야 하므로 인접행렬을 이용, 구성
			adj = new double[N][N];
			isVisited = new boolean[N];
			minEdge = new double[N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					adj[i][j] = Math.pow(pos[i][0]-pos[j][0], 2) + Math.pow(pos[i][1]-pos[j][1], 2); // 환경 부담금이 E*L^2 이므로 제곱근 처리하지 않음.
				}
				minEdge[i] = Double.MAX_VALUE; // 각 정점의 minEdge는 가장 큰 값으로 초기화
			}
			
			double result = 0; // 상수 E를 곱하지 않은 총 환경 부담금
			minEdge[0] = 0; // 0번째 정점에서 출발 
			
			for (int c = 0; c < N; c++) { // 정점 개수만큼 반복
				double min = Double.MAX_VALUE;
				int minVertex = 0;
				//신장트리에 포함되지 않은 정점 중, 신장트리에 포함된 정점들과 연결된 간선의 비용이 최소인 정점 찾기
				for (int i = 0; i < N; i++) {
					if(!isVisited[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				
				result += min; // 총 비용 업데이트
				isVisited[minVertex] = true; // 방문 처리
				
				// 신장트리에 포함되지 않은 정점에 대하여, 신장트리에 포함된 정점으로부터 연결된 간선의 최소 비용을 업데이트 
				// 최적화 : 모든 정점에 대하여 N번 비교 - O(N^2)하는 것이 아니라 
				//		신장트리에 어떤 정점(가장 최소비용인 정점)을 연결할 때마다 업데이트 - O(N)
				// 	 	즉 60-61 line 에서 신장트리에 연결한 정점에 대해서 간선 비용 업데이트 - O(N)
        			// => 마치 a,b,c 중 최소값을 구할 때 a와 b 중 b가 더 작다면, c는 이 b만 대소비교를 하면 되는 것과 같다.(a와 비교 안해도 됨)
				for (int i = 0; i < N; i++) {
					if(!isVisited[i] && adj[minVertex][i] != 0 && minEdge[i] > adj[minVertex][i]) {
						minEdge[i] = adj[minVertex][i];
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(Math.round(E*result)).append("\n");
		}
		System.out.println(sb.toString());
	}

}
