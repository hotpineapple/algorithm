import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
   
    public static void main(String[] args) throws NumberFormatException, IOException {
      // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] arr = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	for (int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
    	st = new StringTokenizer(br.readLine()," ");
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int ans = 0;
        
        for (int i = 0; i < N; i++) {
			    arr[i] -= B;
        }
        ans += N;
        
        for (int i = 0; i < N; i++) {
        	if(arr[i]<=0) continue;
        	
        	ans += arr[i]%C==0 ? arr[i]/C : arr[i]/C+1;
		}
        
        System.out.println(ans);
	}
	
}
