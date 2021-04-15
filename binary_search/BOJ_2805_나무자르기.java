import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); //나무의 개수
		int M = Integer.parseInt(st.nextToken()); //나무의 길이
		int[] len = new int[N];
		st = new StringTokenizer(br.readLine()," ");
        int max = 0;
		for(int i=0;i<N;i++) {
			len[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,len[i]);
		}
		
		int lo = 0, hi = max, result = 0;
		while(lo <= hi) {
			
			int mid = (lo+hi)/2;
			long sum = 0;
			for(int i=0;i<N;i++) {
				if(len[i]>mid) sum += len[i] - mid;
			}

			if(sum>=M) {
                lo = mid+1;
                result = mid;
      }
			else hi = mid-1;
		}
		System.out.println(result);
	}

}
