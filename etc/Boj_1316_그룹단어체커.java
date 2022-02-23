import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if(isGroupWord(br.readLine())) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static boolean isGroupWord(String str) {
		boolean[] isUsed = new boolean[26];
		char cur = str.charAt(0);
		isUsed[cur-97] = true;
		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(isUsed[ch-97] && ch != cur) {
				return false;
			} else {
				cur = ch;
				isUsed[ch-97] = true;
			}
		}

		return true;
	}
}
