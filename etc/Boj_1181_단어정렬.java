import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<List<String>> list = new ArrayList<>();
		for (int i = 0; i <= 50; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			int len = str.length();
			list.get(len).add(str);
		}
		
		for (int i = 0; i <= 50; i++) {
			Set<String> set = new TreeSet<>();
			List<String> subList = list.get(i);
			
			for (int j = 0; j < subList.size(); j++) {
				set.add(subList.get(j));
			}
			subList = new ArrayList<>();
			Iterator<String> it = set.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}

	}
}
