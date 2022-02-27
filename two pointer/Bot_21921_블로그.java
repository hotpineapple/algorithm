import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터
		int max = 0, cnt = 1;
		
		
		for (int i = 0; i < X; i++) {
			max += arr[i];
		}
		int i = 0;
		int sum = max;
		while(i + X < N) {
			
			sum -= arr[i];
			sum += arr[i+X];
			
			if(sum > max) {
				max = sum;
				cnt = 1;
			} else if(sum == max) {
				cnt++;
			}
				
			i++;
		}
		
		// 출력
		System.out.println(max > 0 ? max :"SAD");
		if(max > 0) System.out.println(cnt);
	}

}
