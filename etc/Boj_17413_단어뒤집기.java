import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_스티커2 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < str.length()) {
			char ch = str.charAt(i);
			if(ch=='<') { // 태그 시작이면
				int j = str.indexOf('>',i); // 태그 끝까지 그대로 넣음
//				for(int k=i; k<=j; k++) {
//					sb.append(str.charAt(k));
//				}
				sb.append(str.substring(i,j+1));
				i = j + 1; // 태그 끝 다음 인덱스 부터 봄
			} else {
//				System.out.println("i:"+i);
				int j = i;
				boolean isBlank = false;
				boolean isTag = false;
				while(j < str.length()) {
					char ch2 = str.charAt(j);
					if(ch2 == '<' ) {
						isTag = true;
						break;
					}else if(ch2 == ' ') {
						isBlank = true;
						break;
					}
					j++;
				}
	
				for (int k = j-1; k >= i ; k--) {
					sb.append(str.charAt(k));
				}

				if(isBlank) {
					sb.append(' ');
					i = j + 1;
				} else i = j;
				
			}
		}
		System.out.println(sb.toString());
	}
}

