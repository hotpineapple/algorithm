import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((o1,o2)->(int)(o1-o2));
        for(int i=0;i<scoville.length;i++) pq.offer((long)scoville[i]);
        while(!pq.isEmpty()){
            long min = pq.poll();
            if(min>=K) {
                return answer;
            }
            if(pq.isEmpty()) {
                return -1;
            }
            Long min2 = pq.poll();
            pq.offer(min+min2*2);
            answer++;
        }
        return -1;
    }
}
