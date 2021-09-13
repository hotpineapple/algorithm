
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, output[];
	static boolean[] isSelected;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		output = new int[M];
		
		h(0,1);
		bw.flush();
		bw.close();

	}

	private static void h(int cnt,int start) throws IOException {
//		System.out.println("cnt: "+cnt+", start: "+start);
		
		if(cnt==M){
			print(output);
			return;
		}
		for(int i=start;i<=N;i++) {
			output[cnt] = i;
			h(cnt+1,i);
		}
	}

	private static void print(int[] arr) throws IOException {
		for(int i=0;i<arr.length;i++) {
			bw.write(output[i]+" ");
		}
		bw.newLine();
	}

}
