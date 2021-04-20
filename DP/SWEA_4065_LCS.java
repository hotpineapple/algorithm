Fpackage dp;

import java.util.Scanner;

public class SWEA_4065_LCS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {

			char[] str1 = sc.next().toCharArray();
			char[] str2 = sc.next().toCharArray();
			int len1 = str1.length; 
			int len2 = str2.length; 
			
			int[][] D = new int[len1][len2];
			
			D[0][0] = str1[0]==str2[0] ? 1 : 0;
			for(int i=1;i<len1;i++) D[i][0] = str1[i]==str2[0] ? 1 : str1[i-1]==str2[0] ? 1 : 0;
			for(int i=1;i<len2;i++) D[0][i] = str1[0]==str2[i] ? 1 : str1[0]==str2[i-1] ? 1 : 0;
		
			for (int i = 1; i < len1; i++) {
				for (int j = 1; j < len2; j++) {
					if(str1[i]==str2[j]) {
						D[i][j] = D[i-1][j-1] + 1;
					}else {
						D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
					}
				}
			}

			System.out.println("#"+tc+" "+D[len1-1][len2-1]);
		}

	}

}
