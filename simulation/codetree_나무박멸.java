import java.io.*;
import java.util.*;
public class Main {
    static int N,M,K,C, map[][], spray[][], copyMap[][];
    static long ANS;
    static int[] dr = {-1,1,0,0}; // 상하좌우
    static int[] dc = {0,0,-1,1}; // 상하좌우
    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map=new int[N][N];
        spray = new int[N][N];
        copyMap = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 처리
        for(int i=0;i<M;i++){
            grow();
            // print();
            make();
            // print();
            kill(getMaxKill());
            // print();
            // printSpray();
        }

        //출력
        System.out.println(ANS);
    }
    private static void printSpray() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(spray[i][j]+" ");
            }
             System.out.println();
        }
        System.out.println("=========");
    }
    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
             System.out.println();
        }
        System.out.println("=========");
    }
    private static void reduce() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                spray[i][j]--;
                if(spray[i][j]<0) spray[i][j]=0;
            }
        }
    }
    private static void kill(int[] pos) {
        int r = pos[0];
        int c = pos[1];
        if(r==-1&&c==-1) return;
        reduce();
        
        ANS += map[r][c]; // 해당 칸에도 제초제 뿌리기 때문
        map[r][c] = 0;
        spray[r][c] = C;

        for(int d=0;d<4;d++){
            int cr = r;
            int cc = c;
            int temp = K;
            while(temp>0){
                int nr = cr + dr2[d];
                int nc = cc + dc2[d];
                if(nr<0||nr>=N||nc<0||nc>=N) break;
                if(map[nr][nc]>=0) spray[nr][nc] = C;
                if(map[nr][nc]<=0) break; // 빈칸이거나 벽이면 멈춤
                ANS += map[nr][nc];
                map[nr][nc] = 0;
                cr = nr;
                cc = nc;
                temp--;
            }
        }
    }
    static int dr2[] = {-1,-1,1,1};
    static int dc2[] = {-1,1,-1,1};
    private static int checkKill(int r, int c) {
        if(map[r][c]<=0) return 0;
        int cnt=map[r][c]; // 해당 칸에도 제초제 뿌리기 때문

        for(int d=0;d<4;d++){
            int cr = r;
            int cc = c;
            int temp = K;
            while(temp>0){
                int nr = cr + dr2[d];
                int nc = cc + dc2[d];
                if(nr<0||nr>=N||nc<0||nc>=N) break;
                if(map[nr][nc]<=0) break; // 빈칸이거나 벽이면 멈춤
                cnt += map[nr][nc];
                cr = nr;
                cc = nc;
                temp--;
            }
        }
        return cnt;
    }
    private static int[] getMaxKill() {
        int max = 1;
        int maxR=-1, maxC = -1;
        for(int i=N-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                int res = checkKill(i,j);
                if(res>=max){
                    max = res;
                    maxR = i;
                    maxC = j;
                }
            }
        }
        // System.out.println("spray at "+maxR+","+maxC);
        return new int[] {maxR, maxC};
    }
    private static void make() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>0 ){ // 나무가 있는 칸인지 확인
                    int cnt=0;
                    for(int d=0;d<4;d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr<0||nr>=N||nc<0||nc>=N) continue;
                        if(map[nr][nc]==0 && spray[nr][nc]==0) { // 빈 칸인지 확인, 제초제 확인
                            cnt++;
                        }
                    }
                    for(int d=0;d<4;d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr<0||nr>=N||nc<0||nc>=N) continue;
                        if(map[nr][nc]==0 && spray[nr][nc]==0) {
                            copyMap[nr][nc] += (map[i][j]/cnt); // 번식
                        }
                    }
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = copyMap[i][j];
            }
        }
    }
    private static void grow() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>0){ // 나무가 있는 칸인지 확인
                    int cnt=0;
                    for(int d=0;d<4;d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr<0||nr>=N||nc<0||nc>=N) continue;
                        if(map[nr][nc]>0) cnt++; // 나무가 있는 칸인지 확인
                    }
                    map[i][j]+=cnt;
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                copyMap[i][j] = map[i][j];
            }
        }
    }
}
