import java.io.*;
import java.util.*;
public class Main {
    static int N,M,map[][][],copyMap[][][],cmds[][];
    // static List<int[]> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map=  new int[N][N][2];
        copyMap=  new int[N][N][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        cmds = new int[M][];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            cmds[i] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }

        map[N-2][0][1]=1;map[N-2][1][1]=1;map[N-1][0][1]=1;map[N-1][1][1]=1;
        for(int i=0;i<M;i++){
            // print2();
            moveNgrow(i);
            // print2();
            // print();
            add();
            // print();
            cut();
            // print();
        }
        System.out.println(getAns());
    }
    private static int getAns(){
        int sum=0;
         for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=map[i][j][0];
            }
         }
         return sum;
    }
    private static void cut(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j][0]>=2&&map[i][j][1]==0){
                    map[i][j][0]-=2;
                    map[i][j][1]=1;
                }
            }
        }
         for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j][1]==-1)map[i][j][1]=0;
            }
        }
            
    }
    private static void print2(){
for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j][1]+" ");
            }
            System.out.println();
        }
         System.out.println("==============");
    }
    static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dc = {0,1,1,0,-1,-1,-1,0,1};
    static int[] dr2={-1,-1,1,1};
    static int[] dc2={-1,1,-1,1};
    private static void add(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j][1]==1){
                    int cnt=0;
                    for(int d=0;d<4;d++){
                        int nr = i + dr2[d];
                        int nc = j + dc2[d];
                        if(nr<0||nr>=N||nc<0||nc>=N) continue;
                        if(map[nr][nc][0]>0)cnt++;
                    }
                    // System.out.println("i,j,cnt: "+i+","+j+","+cnt);
                    map[i][j][0]+=cnt;
                    map[i][j][1]=-1;
                }
            }
        }
    }
    private static void moveNgrow(int year){
        int[] cmd = cmds[year];
        int dir = cmd[0];int num = cmd[1];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                 copyMap[i][j][0]=map[i][j][0];
                 copyMap[i][j][1]=map[i][j][1];
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j][1]==1){
                    int nr = i +dr[dir]*num;
                    int nc = j+ dc[dir]*num;
                    if(nr<0) nr+=N;
                    if(nr>=N) nr-=N;
                    if(nc<0) nc+=N;
                    if(nc>=N) nc-=N;
                    // System.out.println(i+","+j+" to "+nr+","+nc);
                    copyMap[nr][nc][1]=2;
                    copyMap[nr][nc][0]++;
                }
            }
        }
         for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                 map[i][j][0]=copyMap[i][j][0];
                 map[i][j][1]=copyMap[i][j][1]==1?0:copyMap[i][j][1]==2?1:0;
            }
        }
    }
    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j][0]+" ");
            }
            System.out.println();
        }
         System.out.println("==============");
    }
}
