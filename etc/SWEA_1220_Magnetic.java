import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1220_Magnetic {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[100][100];
		int ans = 0;
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			ans = 0; //교착상태 개수 초기화
      boolean flag = false; // N극 유무 초기화
			br.readLine(); //무의미한 입력데이터
      
      // 입력
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {					
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
      
			// 열 단위로 위 > 아래로 탐색하며 N극 자석 찾으면 N극 유무 변수를 true로 세팅한다.
			// N극을 찾은 상태에서 S극을 찾으면 교착상태 + 1, N극 유무를 false로 초기화한다.
			// 유의할 점: 테이블 위의 자석끼리는 영향을 주고 받지 않음 (=> ad-hoc)
				
			for (int j = 0; j < 100; j++) {
				flag = false;
				for (int i = 0; i < 100; i++) {
					if(map[i][j] == 1) flag = true;
					if(flag && map[i][j] == 2) {
						ans++;
						flag = false;
					}
				}
			}
			// stringbuilder 이용하여 출력 최적화
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
    // 출력
		System.out.println(sb.toString());
	}
	
}
