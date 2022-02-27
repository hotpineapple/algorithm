import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] arr2 = new int[m];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터
		int i = 0, j = 0, k = 0;
		int len = n + m;
		int[] res = new int[len];
		
		while(k < len) {
			if(arr[i]<=arr2[j]) {
				res[k] = arr[i];
				i++;
				if(i == n) {
					for (int l = k + 1; l < len; l++) {
						res[l] = arr2[j];
						j++;
					}
					break;
				}
			} else {
				res[k] = arr2[j];
				j++;
				if(j == m) {
					for (int l = k + 1; l < len; l++) {
						res[l] = arr[i];
						i++;
					}
					break;
				}
			}
			k++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int l = 0; l < len; l++) {
			sb.append(res[l] + " ");
		}
		System.out.println(sb.toString());
	}

}
