import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++)	pq.offer(Integer.parseInt(br.readLine()));
		int cnt=0;
		while(pq.size()!=1) {
			int num = pq.poll() + pq.poll();
			cnt+=num;
			pq.offer(num);
		}
		
		System.out.println(cnt);
		
	}
}
