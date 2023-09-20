import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0]; int baseCost = fees[1];
        int timeUnit = fees[2]; int costUnit = fees[3];
        Map<String,Integer> totalTimeMap = new TreeMap<>();
        Map<String,String> inTimeMap = new HashMap<>();
        for(int i=0;i<records.length;i++){
            String[] info = records[i].split(" ");
            String time = info[0];
            String[] times = time.split(":");
            int hour = Integer.parseInt(times[0]);
            int min = Integer.parseInt(times[1]);
            String id = info[1];
            String inout = info[2];
            if(inout.equals("IN")){
                inTimeMap.put(id, time);
            }else{ // out
                String[] prevTimes = inTimeMap.get(id).split(":");
                inTimeMap.remove(id);
                int prevHour = Integer.parseInt(prevTimes[0]);
                int prevMin = Integer.parseInt(prevTimes[1]);
                int totalMin = (hour*60+min) - (prevHour*60+prevMin);
                totalTimeMap.put(id, totalTimeMap.getOrDefault(id,0) + totalMin);
            }
        }
        Set<String> set1 = inTimeMap.keySet();
        Iterator<String> it1 = set1.iterator();
        while(it1.hasNext()) {
            String id = it1.next();
            String[] inTime = inTimeMap.get(id).split(":");
            int totalMin = (23*60+59) - (Integer.parseInt(inTime[0])*60+Integer.parseInt(inTime[1]));
            totalTimeMap.put(id, totalTimeMap.getOrDefault(id,0) + totalMin);
        }
            
        Set<String> set = totalTimeMap.keySet();
        int[] answer = new int[set.size()];
        Iterator<String> it = set.iterator();
        int i=0;
        while(it.hasNext()) {
            String key = it.next();
            int totalTime = totalTimeMap.get(key);
            int cost = 0;
            if(totalTime<=baseTime) cost = baseCost;
            else cost = baseCost + (int)(Math.ceil((totalTime-baseTime)/(double)timeUnit)) * costUnit;
            answer [i++] = cost;
        }
        return answer;
    }
}
