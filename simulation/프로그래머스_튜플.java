import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length()-1);
        // 원소 1개
        if(!s.contains("},{")) {
            return new int[]{Integer.parseInt(s.substring(1,s.length()-1))};
        } 
        // 원소 2개 이상
        String[] elements = s.split("\\},\\{");
        elements[0] = elements[0].substring(1,elements[0].length());
        elements[elements.length-1] 
            = elements[elements.length-1].substring(0,elements[elements.length-1].length()-1);
        Arrays.sort(elements, (o1,o2) -> o1.length()-o2.length());
        int[] answer = new int[elements.length];
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<elements.length;i++){
            String str = elements[i];
            String[] nums = str.split(",");
            // System.out.println(str);
            
                for(int j=0;j<nums.length;j++){
                    int num = Integer.parseInt(nums[j]);
                    if(!set.contains(num)){
                        set.add(num);
                        answer[i] = num;
                    }
                }
           
            
        }
        
        return answer;
    }
}
