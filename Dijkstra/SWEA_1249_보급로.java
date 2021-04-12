import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
 
public class SWEA_1249_보급로 {
     
    static class Edge implements Comparable<Edge> {
        int[] pos;
        int cost;
        public Edge(int[] pos, int cost) {
            super();
            this.pos = pos;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
         
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for (int tc = 1; tc <= TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            boolean[][] isVisited = new boolean[N][N];
            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j)-'0'; 
                }
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            dist[0][0] = 0;
            pq.offer(new Edge(new int[] {0,0}, dist[0][0]));
            int[] dr = {-1,0,1,0}; //상 우 하 좌
            int[] dc = {0,1,0,-1};
             
            while(!pq.isEmpty()) {
                Edge curr = pq.poll();
 
                int currR = curr.pos[0];
                int currC = curr.pos[1];
//              System.out.println("pos: "+currR+", "+currC);
                isVisited[currR][currC] = true;
                if(currR == N-1 && currC == N-1) break;
                 
                for (int d = 0; d < 4; d++) {
                     
                    int nextR = currR + dr[d];
                    int nextC = currC + dc[d];
                    if(nextR <0 || nextR==N || nextC<0 || nextC==N || isVisited[nextR][nextC]) continue;
                    if(dist[nextR][nextC] > dist[currR][currC] + map[nextR][nextC]) {
                        dist[nextR][nextC] = dist[currR][currC] + map[nextR][nextC];
                        pq.offer(new Edge(new int[] {nextR,nextC}, dist[nextR][nextC]));
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
