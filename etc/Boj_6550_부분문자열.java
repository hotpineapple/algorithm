import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			String str = br.readLine();
			if(str.isEmpty()) break;
			
			st = new StringTokenizer(str, " ");
			String s = st.nextToken();
			String t = st.nextToken();
			
			if(isSubstring(s,t)) System.out.println("Yes");
			else System.out.println("No");
		}
	}

	private static boolean isSubstring(String s, String t) {
		
		int i=0,j=0;
		while(i<t.length()) {
			char ch = s.charAt(j);
			int index = t.indexOf(ch, i);
			
			if(index<0) return false;
			
			j++;
			i = index + 1;
			if(j == s.length()) return true;
		}
		return true;
	}
}

