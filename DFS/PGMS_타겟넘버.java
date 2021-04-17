class Solution {
    static int answer, targetNum, totalCnt, input[];
    public int solution(int[] numbers, int target) {
        input = numbers;
        targetNum = target;
        totalCnt = numbers.length;
        
        dfs('-',0,0);
        dfs('+',0,0);
        
        return answer;
    }
    private void dfs(char op, int res, int cnt){
              
        if(op=='-') res-=input[cnt];
            
        else res+=input[cnt];
               
        if(cnt == totalCnt-1){
            //System.out.println("result:"+res);
            if(res == targetNum) {
                System.out.println("here");
                answer++;
            }
            return;
        }
        dfs('-',res,cnt+1);
        dfs('+',res,cnt+1);
                
    }
}
