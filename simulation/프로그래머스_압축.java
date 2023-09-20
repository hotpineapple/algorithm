import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<26;i++){
            map.put((char)(65+i)+"",i+1);
        }
        List<Integer> answerList = new ArrayList<>();
        int lastIdx=27;
        for(int i=0;i<msg.length();) {
            String lastStr = "";
            int j=i+1;
            String str="";
            int cnt=0;
            while(j<=msg.length()) {
                str = msg.substring(i,j++);
                if(map.getOrDefault(str,-1) > 0) lastStr = str;
                else break;
                cnt++;
            }
            if(j != msg.length()+1) map.put(str,lastIdx++);
            answerList.add(map.get(lastStr));
            i+=cnt;
        }

        Iterator<Integer> it = answerList.iterator();
        int[] answer = new int[answerList.size()];
        int i=0;
        while(it.hasNext()) {
            int num = it.next();
            answer[i] = num;
            i++;
        }
        return answer;
    }
}
