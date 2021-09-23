import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
	static int N,ans;
	static Consult[] arr; 
	static class Consult {
		int time;
		int price;
		Consult (int time, int price){
			this.time = time;
			this.price = price;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new Consult[N];
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine()," ");
    		arr[i] = new Consult(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
    	subset(0,0);
    	System.out.println(ans);
	}
	private static void subset(int index, int totalPrice) {
		if(index>=N) {
			ans = Math.max(totalPrice, ans);
			return;
		}
		
		Consult c = arr[index];
		int time = c.time;
		int price = c.price;
		
		// 이 상담 건너뜀
		subset(index+1,totalPrice);
		// 이 상담 함 
		if(index + time <= N) subset(index + time,totalPrice + price); // 남은 일수안에 가능한 경우만 상담 할 수 있으므로 조건문 
		
	}
	
}
