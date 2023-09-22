import java.util.*;
class Solution {
    static int cnt;
    static char[][] b;
    public int solution(int m, int n, String[] board) {
        b = new char[board.length][];
        for(int i=0;i<board.length;i++){
            b[i] = board[i].toCharArray();
        }
        while(isRemoved()){
            down();
        }
        return cnt;
    }
    private void down() {
        for(int j=0;j<b[0].length;j++){
            for(int i=b.length-1;i>0;i--){
                if(b[i][j]=='0') {
                    int h = i-1;
                    while(h>=0){
                        if(b[h][j]!='0'){
                            b[i][j] = b[h][j];
                            b[h][j] = '0';
                            break;
                        }
                        h--;
                    }
                }
            }
        }
    }
    private boolean isRemoved() {
        Set<String> set = new HashSet<>();
        int[] dr = {0,1,1};
        int[] dc = {1,0,1};
        for(int i=0;i<b.length-1;i++){
            LOOP:for(int j=0;j<b[0].length-1;j++){
                char ch = b[i][j];
                if(ch=='0') continue;
                for(int d=0;d<3;d++){
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    if(b[nr][nc] != ch) continue LOOP;
                }
                set.add(i+","+j);
                for(int d=0;d<3;d++){
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    set.add(nr+","+nc);
                }
            }
        }
        if(set.size()==0) return false;
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String[] pos = it.next().split(",");
            b[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])] = '0';
        }
        cnt += set.size();
        return true;
    }
}
