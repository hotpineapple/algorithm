import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
//    	String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
    	String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
    	
    	System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
        	String[] pair = clothes[i];
			map.put(pair[1], map.getOrDefault(pair[1], 0) + 1);
		}
        
        for (String key : map.keySet()) {
			answer *= (map.get(key) + 1);
		}
        
        
        return answer - 1;
    }
        
}
