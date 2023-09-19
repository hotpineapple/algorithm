import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int time = 0;
        
        int len = cities.length;
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<len;i++){
            String city = cities[i];
            city = city.toLowerCase();
            int num = map.getOrDefault(city,-1);
            // 시간처리
            if(num==-1) time+=5; // miss
            else time++; // hit
            // cache 넣기
            map.put(city,i);
            // cache 빼기
            if(map.size()>cacheSize){
                int min = 1000000;
                String minCity = null;
                Iterator<String> it = map.keySet().iterator();
                while(it.hasNext()){
                    String city1 = it.next();
                    int num1 = map.get(city1);
                    if(num1 < min) {
                        min = num1;
                        minCity = city1;
                    }
                }
                map.remove(minCity);
            }
        }
        
        return time;
    }
}
