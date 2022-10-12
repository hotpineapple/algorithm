//7:45
import java.io.*;
import java.util.*;
public class Main {
    static int N,M,ANS;
    static int[][] map;
    static boolean[][] vst;
    static int r,c,up=5,down=2,left=4,right=3,top=1,bottom=6,dir=3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<M;i++){
            roll();
        }
       System.out.println(ANS);
    }
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,-1,0,1};
    private static void roll(){
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        //  System.out.println(dir);
        // System.out.println(nr+","+nc);
        if(nr<0||nr>=N||nc<0||nc>=N){
            // System.out.println("reflection");
            switch(dir){
                case 0: dir=2;break;
                case 1: dir=3;break;
                case 2: dir=0;break;
                case 3: dir=1;break;
            }
            nr = r + dr[dir];
            nc = c + dc[dir];
        }
        
        r=nr;
        c=nc;
        
        vst = new boolean[N][N];
        dfs(r,c);
        int temp=-1;
        switch(dir){
            case 2: temp=top;top=down;down=bottom;bottom=up;up=temp;break;
            case 0: temp=top;top=up;up=bottom;bottom=down;down=temp;break;
            case 1: temp=top;top=right;right=bottom;bottom=left;left=temp;break;
            case 3: temp=top;top=left;left=bottom;bottom=right;right=temp;break;
        }
        if(bottom>map[r][c]){
            dir++;
            if(dir==4) dir=0;
        }else if(bottom<map[r][c]){
             dir--;
            if(dir==-1) dir=3;
        }
    }
    private static void dfs(int r, int c){
        vst[r][c] = true;
        ANS+=map[r][c];
        for(int d=0;d<4;d++){
            int nr = r +dr[d];
            int nc = c + dc[d];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(!vst[nr][nc]&&map[nr][nc]==map[r][c]) dfs(nr,nc);
        }
    }
}
