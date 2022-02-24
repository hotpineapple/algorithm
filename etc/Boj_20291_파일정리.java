import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<String,Integer> map = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			String[] arr = str.split("\\.");
			map.put(arr[1], map.getOrDefault(arr[1],0) + 1);
		}
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		List<String> list = new ArrayList<>();
		while(it.hasNext()) {
			list.add(it.next());
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(str+" "+map.get(str));
		}
	}
}

