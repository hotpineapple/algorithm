import java.io.*;
import java.util.*;
public class Main {
    static int N, map[][], copyMap[][],tempMap[][],cnt,ANS,mul;
    static List<Group> groupList = new ArrayList<>();
    static List<int[]> list = new ArrayList<>();
    static Group[] result = new Group[2];
    static boolean vst[][], vst2[][];
    static class Group {
        int num;
        int cnt;
        List<int[]> list;
        Group(int num, int cnt, List<int[]> list){
            this.num = num;
            this.cnt = cnt;
            this.list = list;
        }
    }
    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        map = new int[N][N];
        copyMap = new int[N][N];
        tempMap = new int[N][N];
        vst = new boolean[N][N];
        vst2 = new boolean[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //출력 - 처리
        makeGroup();
        calculateScore();
        for(int i=0;i<3;i++){
            init();
            // print();
            rotate1();
            // print();
            rotate2();
            // print();
            makeGroup();
            calculateScore();
        }
        System.out.println(ANS);
    }
    private static void rotate1(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                copyMap[i][j] = map[i][j];
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==(N)/2 && j<(N)/2){
                    // System.out.println(i+","+j+" to " +(N-1-j)+","+i);
                    copyMap[N-1-j][i] = map[i][j];
                }
                if(i==(N)/2 && j>(N)/2){
                    // System.out.println(i+","+j+" to " +(N-1-j)+","+i);
                    copyMap[N-1-j][i] = map[i][j];
                }
                if(j==(N)/2 && i<(N)/2){
                    //  System.out.println(i+","+j+" to " +j+","+i);
                    copyMap[j][i] = map[i][j];
                }
                
                if(j==(N)/2 && i>(N)/2){
                    // System.out.println(i+","+j+" to " +j+","+i);
                    copyMap[j][i] = map[i][j];
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = copyMap[i][j];
            }
        }
    }
    private static void rotateSquare(int sx, int sy, int squareN) {
        // 정사각형을 시계방향으로 90' 회전합니다.
        for(int x = sx; x < sx + squareN; x++)
            for(int y = sy; y < sy + squareN; y++) {
                // Step 1. (sx, sy)를 (0, 0)으로 옮겨주는 변환을 진행합니다. 
                int ox = x - sx, oy = y - sy;
                // Step 2. 변환된 상태에서는 회전 이후의 좌표가 (x, y) -> (y, squareN - x - 1)가 됩니다.
                int rx = oy, ry = squareN - ox - 1;
                // Step 3. 다시 (sx, sy)를 더해줍니다.
                copyMap[rx + sx][ry + sy] = map[x][y];
            }
    }
    
    private static void rotate2(){
         for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                copyMap[i][j] = map[i][j];
            }
        }
        int sqaureN = N / 2;
        rotateSquare(0, 0, sqaureN);
        rotateSquare(0, sqaureN + 1, sqaureN);
        rotateSquare(sqaureN + 1, 0, sqaureN);
        rotateSquare(sqaureN + 1, sqaureN + 1, sqaureN);
        //copy
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = copyMap[i][j];
            }
        }
    }
    private static void init() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                vst[i][j]=vst[i][j] = false; //주의
            }
        }
        groupList.clear();
        //copymap?
        //tempMap?
    }
    private static void calculateScore() {
        combination(0,0);
    }
    private static void calculateScoreReal() {
        Group g1 = result[0];
        Group g2 = result[1];
        // System.out.println(g1.num+","+g1.cnt+","+g1.list.size());
        // System.out.println(g2.num+","+g2.cnt+","+g2.list.size());
        int score = (g1.cnt + g2.cnt)*g1.num*g2.num;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tempMap[i][j] = 0;
                vst2[i][j] = false;
            }
        }
        for(int i=0;i<g1.list.size();i++){
            tempMap[g1.list.get(i)[0]][g1.list.get(i)[1]] = g1.num;
        }
        for(int i=0;i<g2.list.size();i++){
            tempMap[g2.list.get(i)[0]][g2.list.get(i)[1]] = g2.num;
        }
        // printTemp();
        mul=0;
        getLine(g1.list.get(0));
        // System.out.println(mul);
        score*=mul;
        // System.out.println(score);
        ANS += score;
    }
    private static void print(){
       for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
       }
       System.out.println("===========");
    }
    private static void printTemp(){
       for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(tempMap[i][j]+" ");
            }
            System.out.println();
       }
       System.out.println("===========");
    }
    private static void getLine(int[] pos){
        dfs2(pos[0],pos[1]);
    }
    private static void dfs2(int r, int c){
        vst2[r][c] = true;
        int num = tempMap[r][c];
        for(int d=0;d<4;d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(tempMap[nr][nc]==num && !vst2[nr][nc]) dfs2(nr,nc);
            else if(tempMap[nr][nc]!=num && tempMap[nr][nc]!=0) {
                // System.out.println(nr+","+nc);
                mul++;
            } 
        }
    }
    private static void combination(int cnt, int start){
        if(cnt==2){
            //  계산
            // System.out.println("combi: "+result[0].cnt+","+result[1].cnt);
            calculateScoreReal();

            return;
        }

        for(int i=start;i<groupList.size();i++){
            result[cnt] = groupList.get(i);
            combination(cnt+1, i+1);
        }
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    private static void dfs(int r, int c){
        vst[r][c] = true;
        cnt++;
        list.add(new int[]{r,c});
        int num = map[r][c];
        for(int d=0;d<4;d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(map[nr][nc]==num && !vst[nr][nc]) dfs(nr,nc);
        }
    }
    private static void makeGroup(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!vst[i][j]){
                    cnt=0;
                    list.clear();
                    dfs(i,j);
                    // System.out.println(i+","+j+": "+list.size());
                    // System.out.println("group: "+map[i][j]+","+cnt);
                    List<int[]> temp = new ArrayList<>();
                    for(int k=0;k<list.size();k++){
                        temp.add(list.get(k));
                    }
                    groupList.add(new Group(map[i][j],cnt,temp));
                }
            }   
        }
    }
}
