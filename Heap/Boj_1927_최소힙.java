import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boj_1927_최소힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			
			int num = Integer.parseInt(br.readLine());
			if(num>0) {	
				pq.offer(num);
			}else {
				if(!pq.isEmpty()) System.out.println(pq.poll());
				else System.out.println(0);
			}
		}
	}
}
