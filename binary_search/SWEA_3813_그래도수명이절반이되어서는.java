import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_3813_그래도수명이절반이되어서는 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= TC; tc++) {
			int ans = 0;
			st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] mem = new int[N]; // 플래시 메모리의 wear level
			int[] data = new int[K]; // 데이터의 블록 크기
			
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) mem[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine()," ");
			int sum = 0;
			for (int i = 0; i < K; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				sum += data[i];
			}
			
			int lo = 1, hi = 2000000, mid=0;
			while(lo < hi) {
				mid = (lo + hi)/2;
				if(isAvailable(mem, data, mid, sum)) hi = mid;
				else lo = mid + 1;
			}
			
			sb.append("#").append(tc).append(" ").append(lo).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isAvailable(int[] mem, int[] data, int mid, int sum) {

		boolean flag = false;
		int len = 0, totalLen = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0, size = mem.length;i <size; i++) {
			if(mem[i] <= mid) {
				if(!flag) flag = true;
				len++;
				totalLen++;
			}else if(flag){
				list.add(len);
				len = 0;
				flag = false;
			}
		}
		list.add(len);
		
		if(totalLen < sum) return false;
				
		int limit = list.size();

		for (int i = 0, j = 0, size = data.length; i < size;) {
			if(j == limit) return false;
			
			int dataBlock = data[i];
			int memeBlock = list.get(j);
			
			if(dataBlock < memeBlock) {
				list.set(j, memeBlock - dataBlock);
				i++;
			}else if(dataBlock == memeBlock){
				j++;
				i++;
			}else {
				j++;
			}
		}
		return true;
	}

}
