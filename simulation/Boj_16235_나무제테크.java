import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Cell {
        List<Integer> trees = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();
        int food;
    }
    static int N,M,K,A[][];
    static Cell map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        map = new Cell[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new Cell();
                map[i][j].food = 5;
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());

            map[r][c].trees.add(age);

        }

        for(int i=0;i<K;i++){
            grow();
            change();
            create();
            add();
        }
        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=map[i][j].trees.size();
            }
        }
        System.out.println(sum);
    }

    private static void add() {
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                map[i][j].food += A[i][j];
            }
        }
    }

    private static void create() {
        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> trees = map[i][j].trees;
                for(int k=0;k<trees.size();k++) {
                    if(trees.get(k)%5==0){
                      for(int d=0;d<8;d++){
                          int nr = i+dr[d];
                          int nc = j + dc[d];
                          if(nr<0||nr==N||nc<0||nc==N) continue;
                          map[nr][nc].trees.add(1);
                      }
                    }
                }
            }
        }
    }

    private static void change() {
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> dead = map[i][j].dead;
                int sum=0;
                for(int k=0;k<dead.size();k++){
                    sum+=(dead.get(k))/2;
                }
                map[i][j].food+=sum;
                map[i][j].dead = new ArrayList<>();
            }
        }
    }

    private static void grow() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                List<Integer> newTrees = new ArrayList<>();
                List<Integer> trees = map[i][j].trees;
                int food = map[i][j].food;
                Collections.sort(trees);
                for(int k=0;k<trees.size();k++){
                    if(food>=trees.get(k)){
                        food-=trees.get(k);
                        newTrees.add(trees.get(k)+1);
                    }else{
                        map[i][j].dead.add(trees.get(k));
                    }
                }
                map[i][j].trees = newTrees;
                map[i][j].food = food;
            }
        }
    }
}
