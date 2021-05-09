import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static int[] state = new int[10];
  
    public boolean isHighest(int num){
        
        for(int i=num+1;i<10;i++){
            if(state[i]>0) return false;
        }
        return true;
    }
  
    public int solution(int[] priorities, int location) {
        int answer = 1;   
        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<priorities.length;i++){
            int num = priorities[i];
            state[num]++;
            q.offer(new int[]{num,i});
        }

        while(!q.isEmpty()){
            
            int[] curr = q.poll();
           
            if(isHighest(curr[0])){
                if(curr[1] == location) break;
                answer++;
                state[curr[0]]--;
                
            }else{
                q.offer(curr);
            }
        }        
        
        return answer;
    }
}
