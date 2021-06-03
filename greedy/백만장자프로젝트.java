import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
      
        for(int i = 1; i <= TC; i++) {
            long ans=0;
            int N = Integer.parseInt(br.readLine());
            int[] input = new int[N];
            st = new StringTokenizer(br.readLine()," ");
            
            for(int j = 0; j < N; j++) input[j] = Integer.parseInt(st.nextToken());
            
            int max=0;
            for(int j = N - 1; j >= 0; j--) {
                if(input[j] > max) max = input[j];
                else ans += max - input[j];
            }
          
            sb.append("#").append(i).append(" ").append(ans).append("\n");
        }
      
        System.out.println(sb.toString());   
    }
 
}
