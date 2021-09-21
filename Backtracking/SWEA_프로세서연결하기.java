
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    static List<int[]> list;
    static int N,min,max;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc=1;tc<=TC;tc++) {
            N = Integer.parseInt(br.readLine());
            min=Integer.MAX_VALUE;
            max=Integer.MIN_VALUE;
            int[][] map = new int[N][N];
            list = new ArrayList<>();
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if(num==1) {    
                        if(i!=0 && i!=N-1 && j!=0 && j!=N-1) list.add(new int[] {i,j});
                    }
                }
            }
             
            connect(map,0,0,0);
             
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }
     
    private static void connect(int[][] map, int idx, int len, int cnt) {

        if(idx==list.size()) {

            if(cnt>max) {
                //System.out.println("max겡신:"+cnt+ ", len갱신: "+len);
                max = cnt;
                min = len;
            }else if(cnt==max && len<min) {
                //System.out.println("len 갱신 ");
                //print(map);
                min=len;
            }
            return;
        }
         
        int r = list.get(idx)[0];
        int c = list.get(idx)[1];
         
        //1.가장자리까지 사방탐색
        //1-1.하
        int k=r+1;
        while(k<N&&map[k][c]==0) {
            k++;
        }
        if(k==N) {//아래 연결 가능
            //System.out.println(list.get(idx)[0]+","+list.get(idx)[1]+" 하로 연결");
            k=r+1;
            while(k<N) {
                map[k][c]=2;
                k++;
            }
            connect(map, idx+1, len+(N-1-r),cnt+1);
            //되돌리기
            k=r+1;
            while(k<N) {
                map[k][c]=0;
                k++;
            }
        }
         
        //1-2.상
        k=r-1;
        while(k>=0&&map[k][c]==0) {
            k--;
        }
        if(k==-1) {//아래 연결 가능
            //System.out.println(list.get(idx)[0]+","+list.get(idx)[1]+" 상로 연결");
            k=r-1;
            while(k>=0) {
                map[k][c]=2;
                k--;
            }
            connect(map, idx+1, len+r,cnt+1);
            k=r-1;
            while(k>=0) {
                map[k][c]=0;
                k--;
            }
        }
         
        //1-3.우
        k=c+1;
        while(k<N&&map[r][k]==0) {
            k++;
        }
        if(k==N) {//아래 연결 가능
            //System.out.println(list.get(idx)[0]+","+list.get(idx)[1]+" 우로 연결");
            k=c+1;
            while(k<N) {
                map[r][k]=2;
                k++;
            }
            connect(map,idx+1, len+(N-1-c),cnt+1);
            k=c+1;
            while(k<N) {
                map[r][k]=0;
                k++;
            }
        }
         
        //1-4.좌
         
        k=c-1;
        while(k>=0&&map[r][k]==0) {
            k--;
        }
        if(k==-1) {//아래 연결 가능
            //System.out.println(list.get(idx)[0]+","+list.get(idx)[1]+" 좌로 연결");
            k=c-1;
            while(k>=0) {
                map[r][k]=2;
                k--;
            }
            connect(map, idx+1, len+c,cnt+1);
            k=c-1;
            while(k>=0) {
                map[r][k]=0;
                k--;
            }
        }
      
        //2. 안 연결!!!!
        connect(map, idx+1, len, cnt);
    }
    private static void print(int[][] map) {
        for(int i=0;i<map.length;i++) {
            for(int j=0;j<map[0].length;j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
