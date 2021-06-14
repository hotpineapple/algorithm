import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
//    	String[] participant = {"leo", "kiki", "eden"};
//    	String[] completion = {"eden", "kiki"};
    	
//    	String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//    	String[] completion = {"josipa", "filipa", "marina", "nikola"};
    	
    	String[] participant = {"mislav", "stanko", "mislav", "ana"};
    	String[] completion = {"stanko", "ana", "mislav"};
    	
    	System.out.println(solution(participant,completion));
    }

    public static String solution(String[] participant, String[] completion) {
        
        String answer = "";
        
        Map<String,Integer> map = new HashMap<>();
        for (String c : completion) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
        
        for(String p : participant) {
        	int num = map.getOrDefault(p, 0);
        	if(num == 0) answer = p;
        	else map.put(p, num-1);
        }
        
        
        return answer;
    }
}
