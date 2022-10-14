import java.io.*;
import java.util.*;
public class Main {
    static int N, map[][], sum;
    static boolean[][] vst;
    static class Student {
        int num;
        List<Integer> list;
        Student(int num, List<Integer> list){
            this.num = num; this.list = list;
        }
    }
    static Student[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        arr = new Student[N*N];
        map = new int[N][N];
        vst = new boolean[N][N];
        for(int i=0;i<N*N;i++){
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            int num = Integer.parseInt(st.nextToken());
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));     
            arr[i] = new Student(num, list);
        }
        for(int i=0;i<N*N;i++){
            Student s = arr[i];
            find(s.num, s.list);
            // print();
        }
        
        for(int i=0;i<N*N;i++){
            Student s = arr[i];
            getScore(s.num, s.list);
        }
        System.out.println(sum);
    }
    private static void print(){
         for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
                }
                System.out.println();
         }
         System.out.println("==========");
    }
    private static void getScore(int num, List<Integer> list){
        for(int i=N-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                if(map[i][j]!=num) continue;
                int cnt = 0;
                for(int d=0;d<4;d++){
                    int nr = i+dr[d];
                    int nc = j + dc[d];
                    if(nr<0||nr>=N||nc<0||nc>=N) continue;
                    for(int k=0;k<list.size();k++){
                        if(map[nr][nc]==list.get(k)) cnt++;
                    }
                }
                // System.out.println(i+","+j+": "+cnt);
                sum+=Math.pow(10,cnt-1);
            }
        }
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    private static void find(int num, List<Integer> list){
        // 좋아하는친구많은칸
        int max=-1;
        List<int[]> pos = new ArrayList<>();
        for(int i=N-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                if(vst[i][j]) continue;
                int cnt = 0;
                for(int d=0;d<4;d++){
                    int nr = i+dr[d];
                    int nc = j + dc[d];
                    if(nr<0||nr>=N||nc<0||nc>=N) continue;
                    for(int k=0;k<list.size();k++){
                        if(map[nr][nc]==list.get(k)) cnt++;
                    }
                }
                // if(num==8) 
                if(max<cnt){ pos.clear(); pos.add(new int[]{i,j});max = cnt;}
                else if(max==cnt){ pos.add(new int[]{i,j});}
            }
        }
        
        if(pos.size()==1){
            int[] p = pos.get(0);
            map[p[0]][p[1]] = num;
            vst[p[0]][p[1]] = true;
            return;
        }
        // 빈칸 많은 칸
        max=-1;
        List<int[]> pos2 = new ArrayList<>();
        for(int i=0;i<pos.size();i++){
            int[] p = pos.get(i);
            int r = p[0];
            int c = p[1];
            // System.out.println(p[0]+","+p[1]);
                if(vst[r][c]) continue;
                int cnt = 0;
                for(int d=0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c + dc[d];
                    if(nr<0||nr>=N||nc<0||nc>=N) continue;
                    if(map[nr][nc]==0) cnt++;
                }
                // System.out.println(r+","+c+": "+cnt);
                if(max<cnt){ pos2.clear(); pos2.add(new int[]{r,c});max = cnt;}
                else if(max==cnt){ pos2.add(new int[]{r,c});}
            
        }
        // System.out.println(pos2.size());
       if(pos2.size()==1){
            int[] p = pos2.get(0);
            int r = p[0];
            int c = p[1];
            map[r][c] = num;
            vst[r][c] = true;
            // System.out.println(r+","+c+": "+num);
            return;
        }
        // 행
        int minR=N;
        List<int[]> pos3 = new ArrayList<>();
        for(int i=0;i<pos2.size();i++){
            int[] p = pos2.get(i);
            int r = p[0];
            int c = p[1];
            if(minR>r){ pos3.clear(); pos3.add(new int[]{r,c});minR = r;}
            else if(minR==r){ pos3.add(new int[]{r,c});}
        }
        if(pos3.size()==1){
            int[] p = pos3.get(0);
            int r = p[0];
            int c = p[1];
            map[r][c] = num;
            vst[r][c] = true;
            // System.out.println(r+","+c+": "+num);
            return;
        }
        // 열
        int minC=N;
        List<int[]> pos4 = new ArrayList<>();
        for(int i=0;i<pos3.size();i++){
            int[] p = pos3.get(i);
            int r = p[0];
            int c = p[1];
            if(minC>c){ pos4.clear(); pos4.add(new int[]{r,c});minC = c;}
            else if(minC==c){ pos4.add(new int[]{r,c});}
        }
        if(pos4.size()==1){
            int[] p = pos4.get(0);
            int r = p[0];
            int c = p[1];
            map[r][c] = num;
            vst[r][c] = true;
            // System.out.println(r+","+c+": "+num);
            return;
        }
    }
}
