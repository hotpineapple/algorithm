import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> adj = new ArrayList<>();
    static int cnt,target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        int[] p = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        int root = -1;
        for(int i=0;i<n;i++){
            if(p[i]>=0) adj.get(p[i]).add(i);
            else root = i;
        }
        target = Integer.parseInt(br.readLine());
        if(target==root) {
            System.out.println(cnt);
            return;
        }
        adj.get(p[target]).remove(Integer.valueOf(target));
        dfs(root);
        System.out.println(cnt);
    }
    private static void dfs(int v){
        List<Integer> children = adj.get(v);
        if(children.size()==0) {
            cnt++;
            return;
        }
        for(int i=0;i<children.size();i++){
            dfs(children.get(i));
        }
    }
}
