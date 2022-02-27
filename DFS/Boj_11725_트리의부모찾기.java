import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> adj = new ArrayList<>();
	static boolean[] vst;
	static int[] ans;
	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		vst = new boolean[N+1];
		ans = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			adj.add(new ArrayList<>());
		}
		StringTokenizer st = null;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		// set depth
		dfs(1);
		
		// 출력
		for (int i = 2; i <= N; i++) {
			System.out.println(ans[i]);
		}
	}

	private static void dfs(int num) {
		vst[num] = true;
		List<Integer> list = adj.get(num);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			int child = list.get(i);
			
			if(vst[child]) continue;
			
			ans[child] = num;
			dfs(child);
		}
		
	}

}
