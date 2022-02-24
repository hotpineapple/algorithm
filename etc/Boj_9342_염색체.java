import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_동전2 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			System.out.println(isPossible(br.readLine()) ? "Infected!" : "Good");
		}
	}

	private static boolean isPossible(String str) {
		
		char ch = str.charAt(0);
		
		if(ch == 'A') {
			char cur = ch;
			for (int i = 1; i < str.length(); i++) {
				
				char ch2 = str.charAt(i);
				if(cur == 'A' && ch2 != 'A' && ch2!='F') return false;
				if(cur == 'F' && ch2 != 'F' && ch2!='C') return false;
				if(cur == 'C') {
					if(ch2 != 'C') {
						return false;
					}
				}
				cur = ch2;
			}
			return true;
		} else {
			char ch2 = str.charAt(1);
			if(ch2 != 'A') return false;
			
			char cur = ch2;
			for (int i = 2; i < str.length(); i++) {
				
				char ch3 = str.charAt(i);
				if(cur == 'A' && ch3 != 'A' && ch3!='F') return false;
				if(cur == 'F' && ch3 != 'F' && ch3!='C') return false;
				if(cur == 'C') {
					if(ch3 != 'C') {
						return false;
					}
				}
				cur = ch3;
			}
			return true;
		} 

	}
}

