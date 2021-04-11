import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 정해진 총액 이하에서 가능한 최대의 총 예산 구하기 => 이분탐색
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 지방의 수
		int[] req = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int sum = 0, max = 0;
		for (int i = 0; i < N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
			if(req[i] > max) max = req[i];
			sum += req[i];
		}
		int limit = Integer.parseInt(br.readLine()); //총 예산
		
		if(sum <= limit) { // case 1. 모든 요청이 배정될 수 있는 경우 => 그대로 배정
			System.out.println(max);
			return;
		}
		
		// case 2. 모든 요청이 배정될 수 없는 경우 => 상한액 정하기
		int lo = 0, hi = max; 
		int mid = 0, temp = 0;
		while(lo + 1 < hi) {
			mid = (lo + hi) / 2;
			temp = 0;
			for(int i=0; i<N; i++) temp += (req[i] > mid ? mid : req[i]);
			if(temp > limit) {
				hi = mid;
			}else if(temp <= limit){
				lo = mid;
			}
		}
		System.out.println(lo);		
		
	}

}
