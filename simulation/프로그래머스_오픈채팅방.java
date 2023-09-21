import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        List<String> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<record.length;i++){
            String[] rec = record[i].split(" ");
            String op = rec[0]; String id = rec[1];
            if(op.equals("Enter")) {
                list.add(id+":님이 들어왔습니다.");
                map.put(id,rec[2]);
            }else if(op.equals("Leave")){
                list.add(id+":님이 나갔습니다.");
            }else if(op.equals("Change")){
                map.put(id,rec[2]);
            }
        }
        String[] answer = new String[list.size()];
        int idx=0;
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String[] msg = it.next().split(":");
            String id = msg[0];
            String others = msg[1];
            answer[idx++] = map.get(id)+others;
        }
        return answer;
    }
}
