class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        for(int i=0;i<4;i++) dp[0][i] = land[0][i];
        for(int i=1;i<land.length;i++){
            for(int j=0;j<4;j++){
                switch(j){
                    case 0:
                        dp[i][j] = Math.max(Math.max(dp[i-1][1],dp[i-1][2]),dp[i-1][3]) + land[i][j];
                        break;
                    case 1:
                        dp[i][j] = Math.max(Math.max(dp[i-1][0],dp[i-1][2]),dp[i-1][3]) + land[i][j];
                        break;
                    case 2:
                        dp[i][j] = Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][3]) + land[i][j];
                        break;
                    case 3:
                        dp[i][j] = Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]) + land[i][j];
                        break;
                    default:
                        break;
                
                }
            }
        }
        return Math.max(Math.max(dp[land.length-1][0],dp[land.length-1][1]),Math.max(dp[land.length-1][2],dp[land.length-1][3]));
    }
}
