class Solution {
    static boolean[] vst;
    static int answer;
    
    public int solution(int n, int[][] computers) {

        vst = new boolean[n];
        for(int i = 0; i< n; i++){
            if(!vst[i]) {
                System.out.println("start:"+i);
                answer++;
                dfs(i, computers); 
            }
        }
        
        return answer;
    }
    private void dfs(int i, int[][] computers){
        System.out.println("i:"+i);
        vst[i] = true;
        
        for(int j=0;j<computers.length;j++){
            if(!vst[j] && i!=j && computers[i][j] == 1) dfs(j, computers);
        }
    }
}
