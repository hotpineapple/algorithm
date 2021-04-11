import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1954_달팽이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		int N = 0;
		for (int tc = 1; tc <= TC; tc++) {		
			N = Integer.parseInt(br.readLine());
			snail(tc, N);
		}

	}

	private static void snail(int tc, int n) {
		int[][] snail = new int[n][n];
		
		// 오른쪽 > 아래 > 왼쪽 > 위 방향으로 회전
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		int d = 0; // 이동 방향
		int r = 0, c = 0, num = 1; //현재 행, 열, 칸에 채워질 숫자를 저장하는 변수
		
		int nr = 0, nc = 0; // 다음 행, 열을 저장하는 변수, 그냥 r과 c만 사용해도 된다.
		while(num <= n * n) {
			
			snail[r][c] = num++;
			
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr < 0 || nr == n || nc < 0 || nc == n || snail[nr][nc] != 0) { // 다음 위치가 배열의 크기를 벗어나거나 이미 숫자가 채워진 칸인 경우
				d = (d + 1) % 4; //다음 방향으로 방향 변경 (모듈러스 연산을 이용하여 위 > 오른쪽 변환 가능)
				nr = r + dr[d];
				nc = c + dc[d];
			}
      
      // r과 c 값 갱신 (재귀함수 호출식 풀이에만 익숙해져 있어서 반복문 풀이에서 꼭 필요한 이 부분을 간과했다.)
			r = nr; 
			c = nc;
		}
		
    //출력
		System.out.println("#" + tc);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(snail[i][j]+" ");
			}
			System.out.println();
		}
	}

}
