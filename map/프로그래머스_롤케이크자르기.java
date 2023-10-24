import java.util.*;
class Solution {
    public int solution(int[] topping) {
        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        for(int i=0;i<topping.length;i++){
            int cnt = map2.getOrDefault(topping[i],0);
            map2.put(topping[i],cnt+1);
        }
        int cnt =0;
        for(int i=0;i<topping.length;i++){
            int cnt1 = map1.getOrDefault(topping[i],0);
            map1.put(topping[i],cnt1+1);
            int cnt2 = map2.get(topping[i]);
            if(cnt2-1==0)map2.remove(topping[i]);
            else map2.put(topping[i],cnt2-1);

            if(map1.keySet().size() == map2.keySet().size()) {
                cnt++;
            }
        }
                                        
        return cnt;
    }
}
