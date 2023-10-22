import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 1;
        int n = maps.length; int m = maps[0].length;
        boolean[][] vst = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        vst[0][0] = true;
        q.offer(new int[]{0,0});
        int[] dr = {-1,1,0,0}; int[] dc = {0,0,-1,1};
        boolean success=false;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                int[] cur = q.poll();
                int r = cur[0]; int c = cur[1];
                // System.out.println(r+","+c);
                for(int d=0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0||nr>=n||nc<0||nc>=m||vst[nr][nc]||maps[nr][nc]==0) continue;
                    else if(nr==n-1 && nc==m-1) {
                        success=true;
                        return answer+1;
                    }
                    vst[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }
            }
            answer++;
        }
        return success ? answer : -1;
    }
}
