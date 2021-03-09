import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Boj_11286_절댓값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<int[]> pq 
			= new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					int res = o1[0] - o2[0];
					if(res==0) return o1[1] - o2[1];
					else return res;
				}
			});

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			
			int num = Integer.parseInt(br.readLine());
			if(num>0) {	
				pq.offer(new int[] {num,num});
			}else if(num<0){
				pq.offer(new int[] {num*(-1),num});
			}else {
				if(!pq.isEmpty()) System.out.println(pq.poll()[1]);
				else System.out.println(0);
			}
		}
	}

}
