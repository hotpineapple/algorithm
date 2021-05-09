import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<progresses.length;i++) 
            q.offer((100-progresses[i]) % speeds[i] == 0 ? (100-progresses[i]) / speeds[i] : (100-progresses[i]) / speeds[i] + 1);
        
        int cmp = q.poll();
        ArrayList<Integer> list = new ArrayList<>();
        int ans = 1;

        while(!q.isEmpty()){

            int tmp = q.poll();
            if(tmp <= cmp) {
                ans++;
            }
            else {
                list.add(ans);
                ans = 1;
                cmp = tmp;

            }
        }
        list.add(ans);
        
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++) answer[i] = list.get(i);
        
        return answer;
    }
}
