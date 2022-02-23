import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = br.readLine();
			
			if(str.equals("end")) break;

			if(isAcceptable(str)) {
				System.out.println("<" + str + "> is acceptable.");
			} else {
				System.out.println("<" + str + "> is not acceptable.");
			}
		}
	}

	private static boolean isAcceptable(String str) {
		boolean atLeastOneMoem = false;
		int moemCnt = 0;
		int jaemCnt = 0;
		char prev = ' ';
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if(ch == prev && ch != 'e' && ch != 'o') return false;
			
			if(ch == 'a' ||ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u') {
				atLeastOneMoem = true;
				moemCnt++;
				jaemCnt = 0;
			}else {
				jaemCnt++;
				moemCnt = 0;
			}
			
			if(moemCnt > 2 || jaemCnt > 2) {
				return false;
			}
			prev = ch;
		}
		if(!atLeastOneMoem) return false;
		return true;
	}
}
