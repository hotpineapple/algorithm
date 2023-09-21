import java.util.*;
class Solution {
    public int solution(String dirs) {
        int r=0; int c=0;
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        Set<String> set = new HashSet<>();
        int answer = 0;
        for(int i=0;i<dirs.length();i++){
            char ch = dirs.charAt(i);
            int nr=-1; int nc=-1;
            switch(ch){
                case 'U':
                    nr = r+dr[0];
                    nc = c+dc[0];
                    break;
                case 'D':
                    nr = r+dr[1];
                    nc = c+dc[1];
                    break;
                case 'L':
                    nr = r+dr[2];
                    nc = c+dc[2];
                    break;
                case 'R':
                    nr = r+dr[3];
                    nc = c+dc[3];
                    break;
                default:
                    break;
            }
            if(nr<-5||nr>5||nc<-5||nc>5) continue;
            else if(!set.contains(r+" "+c+" "+nr+" "+nc) && !set.contains(nr+" "+nc+" "+r+" "+c)){
                answer ++;
                set.add(r+" "+c+" "+nr+" "+nc);
            }
            r= nr;
            c= nc;
        }
        return answer;
    }
}
