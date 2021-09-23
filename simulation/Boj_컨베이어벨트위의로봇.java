
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
    static class Cell {
    	int worn; //내구도
    	boolean hasRobot; // 칸에 로봇 올라와있는지 여부
    	int order = -1; //로봇 있는 경우 올라온 순서 = 단계
    	
    	Cell(int worn){
    		this.worn = worn;
    	}
    }
   
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine()," ");
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	Cell[] arr = new Cell[2*N];
    	st = new StringTokenizer(br.readLine()," ");
    	for (int i = 0; i < 2*N; i++) {
			arr[i] = new Cell(Integer.parseInt(st.nextToken()));
		}
    	int level = 0; // 단계
    	int put = 0; // 올리는 위치 번호
    	int get = N-1; // 내리는 위치 번호
    	int cnt = 0; // 내구도 0인 칸 개수
    	while(cnt < K) {
//    		System.out.println("1.벨트 회전");
    		// 위치 조정 (컨베이어 벨트가 돌아가는 것을 의미)
    		put = put==0 ? 2*N-1 : put-1;
    		get = get==0 ? 2*N-1 : get-1;

    		// 내릴 로봇 있는지 확인
    		if(arr[get].hasRobot) {
//    			System.out.println("*.로봇 내림");
    			arr[get].hasRobot = false;
    			arr[get].order = -1;
    		}

    		// 로봇 이동할 수 있는지 확인 - 가장 먼저 올라온 로봇부터
    		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[0] - o2[0]);
    		
    		for (int i = 0; i < 2*N; i++) {
				if(arr[i].hasRobot) pq.offer(new int[] {arr[i].order, i});
			}
    		
    		while(!pq.isEmpty()) {
    			int index = pq.poll()[1];
    			if(arr[index].hasRobot && !arr[(index+1)%(2*N)].hasRobot && arr[(index+1)%(2*N)].worn>0) {
//        			System.out.println("2.로봇 한칸 이동");
        			arr[(index+1)%(2*N)].hasRobot = true;
        			arr[(index+1)%(2*N)].worn--;
        			if(arr[(index+1)%(2*N)].worn==0) cnt++;
        			arr[(index+1)%(2*N)].order = arr[index].order;
        			
        			arr[index].hasRobot = false;
        			arr[index].order = -1;
        		}
    		}
    		// 내릴 로봇 있는지 확인
    		if(arr[get].hasRobot) {
//    			System.out.println("*.로봇 내림");
    			arr[get].hasRobot = false;
    			arr[get].order = -1;
    		}

    		// 올릴 위치 확인 후 올림
    		if(arr[put].worn > 0) {
//    			System.out.println("3.로봇 올림");
    			arr[put].hasRobot = true;
    			arr[put].order = level+1;
    			arr[put].worn--;
    			if(arr[put].worn==0) cnt++;
    		}

    		level++;
    	}
 
        System.out.println(level);
	}
	
}
