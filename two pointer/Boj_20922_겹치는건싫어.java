import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] cnt = new int[100001];
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0, left = 0, right = 0;
        cnt[arr[0]] = 1;
        while(left <= right && right < N) {

        	if(cnt[arr[right]] > K) { // 중복발생
        		max = Math.max(max, right - left);
        		cnt[arr[left]]--;
        		left++;
        	}else {
        		if(right == N-1) {
        			max = Math.max(max, right - left + 1);
        			break;
        		}
        		cnt[arr[++right]]++;
        	}
        }
        System.out.println(max);
    }
}
