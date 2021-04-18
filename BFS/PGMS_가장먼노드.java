import java.util.*;
class Solution {
    class Edge {
        int no;
        Edge next;
        Edge(int no, Edge next) {
            this.no = no;
            this.next = next;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        Edge[] adj = new Edge[n+1];
        boolean[] vst = new boolean[n+1];
        for(int i=0;i<edge.length;i++){
            int from = edge[i][0];
            int to = edge[i][1];
            adj[from] = new Edge(to, adj[from]);
            adj[to] = new Edge(from,adj[to]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        vst[1] = true;
        
        int level = 0;
        int size = 0;
        while(!q.isEmpty()){
            int temp = size = q.size();
            while(temp-->0){
                int curr = q.poll();
                for(Edge e = adj[curr]; e!=null ;e = e.next){
                    if(!vst[e.no]) {
                        q.offer(e.no);
                        vst[e.no] = true;
                    }
                }
            }
            level++;
        }
        return answer = size;
    }
}
