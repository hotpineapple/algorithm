class Solution {
  
  static int isSelected, answer, input[], output[];
	static boolean pool[];
  
	public static int solution(String numbers) {
    
		int size = numbers.length();
		input = new int[size];
		pool = new boolean[10000000];
		
		for (int i = 0; i < size; i++) input[i] = numbers.charAt(i) - '0';
		
    for(int i = 1; i <= size; i++) {
			output = new int[i];
			p(0,i,0);
		}
		
		return answer;
	}
	
	private static void p(int cnt, int N, int isSelected) {
		if(cnt == N) {
			//System.out.println(Arrays.toString(output));
			//숫자 만든 후 소수 판별
			int num = make(output);
			if(!pool[num] && isSosu(num)) answer++;
			return;
		}
		
		for(int i = 0; i<input.length; i++) {
			
			if((isSelected & 1<<i) !=0) continue;
			output[cnt] = input[i];
	
			p(cnt+1,N,isSelected | 1<<i);

		}
		
	}

	private static boolean isSosu(int num) {
		pool[num] = true;
		if(num==0||num==1) return false;
		for(int i=2;i<num;i++) {
			if(num % i == 0) return false;
		}
//		System.out.println(num+"는 소수");
		return true;
	}

	private static int make(int[] numbers) {
		int res=0;
		for(int i=0;i<numbers.length;i++) res += numbers[numbers.length-i-1] * Math.pow(10, i);
			
//		System.out.println("res="+res);

		return res;
	}
}
