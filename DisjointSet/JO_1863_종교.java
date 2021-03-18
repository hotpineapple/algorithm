import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1863_종교 {

	static int N;
	static int[] parents;
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = -1;
		}
	}
	static int findSet(int a) {
		if(parents[a]<0) return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		make();
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine()," ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			union(i,j);
		}
		
		int cnt=0;
		for(int i = 1; i <= N; i++) {
			if(parents[i]<0) cnt++;
		}
		
		System.out.println(cnt);

	}

}
