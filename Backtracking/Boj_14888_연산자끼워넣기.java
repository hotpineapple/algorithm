import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, N, arr[], op[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		op = new int[4];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < op.length; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		// 백트래킹
		for (int i = 0; i < op.length; i++) {
			if(op[i] > 0) {
				op[i]--;
				calculate(arr[0], 1, i);
				op[i]++;
			}
		}
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void calculate(int num, int nextIndex, int operator) {
		
		int num1 = num;
		int num2 = arr[nextIndex];
		int res = 0;
		
		switch(operator) {
		case 0:
			res = num1 + num2;
			break;
		case 1:
			res = num1 - num2;
			break;
		case 2:
			res = num1 * num2;
			break;
		case 3:
			res = num1 / num2;
			break;
		}
		
		for (int i = 0; i < op.length; i++) {
			if(op[i] > 0) {
				op[i]--;
				calculate(res, nextIndex + 1, i);
				op[i]++;
			}
		}
		
		if(nextIndex == N - 1) { // 기저조건
			max = Math.max(max, res);
			min = Math.min(min, res);
		}
	}

}
