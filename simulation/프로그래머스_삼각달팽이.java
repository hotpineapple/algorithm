class Solution {
    public int[] solution(int n) {
        int len = n; int num = 1;
        int[][] arr = new int[2*n-1][2*n-1];
        int r=0;int c=n-1;int d=0;
        int[] dr = {1,0,-1};
        int[] dc = {-1,2,-1};
        while(len>0){
            for(int i=0;i<len;i++){
                arr[r][c] = num++;
                if(i==len-1) {
                    d++;
                    if(d==3) d=0;
                }
                 r=r+dr[d];
                 c=c+dc[d];
            }
            len--;
        }
        int[] answer = new int[n*(n+1)/2];
        int idx=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]!=0) answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }
}
