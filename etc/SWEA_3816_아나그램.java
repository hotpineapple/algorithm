import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3816_아나그램 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= TC; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine()," ");
			char[] s1 = st.nextToken().toCharArray();
			char[] s2 = st.nextToken().toCharArray();
			int[] cnt1 = new int[26]; // 아나그램 판별을 위한 s1의 알파벳 개수를 카운트하는 배열
			int[] cnt2 = new int[26]; // s2
			
			for (char ch : s1) cnt1[ch-'a']++;

			int len1 = s1.length, len2 = s2.length;
			for (int i = 0; i<len1; i++) cnt2[s2[i]-'a']++;
			
			if(isAnagram(cnt1, cnt2)) ans++;
			
			for (int i = 0, size = len2 - len1; i < size; i++) {
				cnt2[s2[i]-'a']--;
				cnt2[s2[i+len1]-'a']++;
				
				if(isAnagram(cnt1, cnt2)) ans++;
			}		
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static boolean isAnagram(int[] cnt1, int[] cnt2) {
		for (int j = 0; j < 26; j++) {
			if(cnt1[j] != cnt2[j]) return false;
		}
		return true;
	}

}
