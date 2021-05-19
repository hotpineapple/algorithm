import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] answers) {
        	ArrayList<Integer> list = new ArrayList<>();
		int[] res = new int[3];
		int[] p1 = {1,2,3,4,5};
		int[] p2 = {2,1,2,3,2,4,2,5};
		int[] p3 = {3,3,1,1,2,2,4,4,5,5};

		int size = answers.length;
		for (int i = 0; i < size; i++) {

			int ans = answers[i];
			if(p1[i % 5] == ans) res[0]++;
			if(p2[i % 8] == ans) res[1]++;
			if(p3[i % 10] == ans) res[2]++;
		}
		
		int max = 0;
		for(int i = 0; i < 3; i++) {
			max = Math.max(max, res[i]);
		}
		for(int i = 0; i < 3; i++) {
			if(res[i] == max) {
				list.add(i+1);
			}
		}
		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
    }
}
