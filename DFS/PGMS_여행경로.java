import java.util.*;
class Solution {
    static List<String> answer;
    static int totCnt;
    static boolean[] vst;
    
    public String[] solution(String[][] tickets) {
       
        totCnt = tickets.length;
        vst = new boolean[totCnt];
        answer = new ArrayList<>();

        for(int i = 0; i<totCnt; i++){
            if(tickets[i][0].equals("ICN")) {
                vst[i] = true;
                dfs(tickets[i][1],"ICN,"+tickets[i][1],1,tickets);  
                vst[i] = false;
            }
        }
        Collections.sort(answer);
        return answer.get(0).split(",");
    }
    private void dfs(String dest,String concat,int cnt,String[][] tickets){
      
        if(cnt == totCnt){
            answer.add(concat);
            return;
        }

        for(int i = 0; i<totCnt; i++){
            if(!vst[i] && tickets[i][0].equals(dest)) {
                 vst[i] = true;
                dfs(tickets[i][1],concat+","+ tickets[i][1],cnt+1,tickets);
                vst[i] = false;
            }
        }
    }
}
