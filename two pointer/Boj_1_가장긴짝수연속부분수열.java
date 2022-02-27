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
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0, left = 0, right = 0, cnt = 0;
        while(left <= right && right < N) {
//        	System.out.println("l,r: "+ left+", "+right );
        	if(arr[right]%2 != 0) {
        		if(cnt < K) {
//        			System.out.println("홀수지움");
        			cnt++;
        			max = Math.max(max, right - left-cnt);
//        			System.out.println("max: "+max);
        			right++;
        		}else {
//        			System.out.println("지우는횟수초과해서 left 땡겨옴");
        			if(arr[left]%2 !=0) {
        				cnt--;
        			}
        			left++;

        		}
        	}else {
//        		System.out.println("짝수임");
        		max = Math.max(max,right-left+1-cnt);
//        		System.out.println("max: "+max);
        		right++;
        	}
        }
        
        System.out.println(max);
    }
}
