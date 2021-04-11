import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cnt = null;
		StringTokenizer st = null;
		int limit = 0;
		StringBuilder sb = new  StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			cnt = new int[101]; //테스트케이스마다 초기화해야함에 유의
			limit = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
			int max = 0, min = 101;
			for (int i = 0; i < 100; i++) {
				int num = Integer.parseInt(st.nextToken());
				cnt[num]++; // 상자의 높이(1~100)를 인덱스로 하여 이 높이의 상자 개수를 저장함
				if(num > max) max = num;
				if(num < min) min = num;
			}

			while(limit-- > 0) { // 주어진 횟수만큼 덤프
				if(max-min <= 1) break; // 최고점 - 최저점 차이가 1 이하라면 덤프를 계속하더라도 더 이상 의미가 없으므로 반복문 빠져나감
				
				++cnt[max-1];
				if(--cnt[max] == 0) max--;
				++cnt[min+1];
				if(--cnt[min] == 0) min++;
			}
			sb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb.toString());
	}

}
