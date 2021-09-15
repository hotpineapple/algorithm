import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L,C;
	static char input[], output[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[C];
		output = new char[L];
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		combination(0,0);
	}
	private static void combination(int cnt, int start) {
		if(cnt == L) { 
			if(checkCondition()) {
				for (int i = 0; i < L; i++) {
					System.out.print(output[i]);
				}
				System.out.println();
			}
			return;
		}
		for (int i = start; i < C; i++) {
			output[cnt] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
	private static boolean checkCondition() {
		int jaeumCnt = 0, moeumCnt = 0;
		for (int i = 0; i < L; i++) {
			if(output[i]=='a'||output[i]=='e'||output[i]=='i'||output[i]=='o'||output[i]=='u')
				moeumCnt++;
			else jaeumCnt++;
		}
		if(jaeumCnt>=2 && moeumCnt >= 1) return true;
		return false;
	}

}
