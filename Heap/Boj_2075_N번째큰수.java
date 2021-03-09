import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_2075_N번째큰수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
	
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) pq.offer(Integer.parseInt(st.nextToken()));
		
		for (int i = 1; i < N; i++)	{
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) pq.offer(Integer.parseInt(st.nextToken()));
			for (int j = 0; j < N; j++)	pq.poll();
		}
		
		
		System.out.println(pq.poll());
	}
}
