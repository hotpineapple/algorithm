import java.util.*;
class Solution {
    static class FileName {
        String head; String number; String tail; int idx;
        FileName(String head, String number, String tail, int idx) {
            this.head = head; this.number= number; this.tail = tail; this.idx = idx;
        }
    }
    public String[] solution(String[] files) {
        FileName[] list = new FileName[files.length];
        for(int i=0;i<files.length;i++) {
            String file = files[i];
            int idx = 0;
            while(idx<file.length()){
                char ch = file.charAt(idx);
                if(ch>='0' && ch<='9') break;
                idx++;
            }
            String head = file.substring(0,idx);
            int idx2 = idx;
            while(idx2<file.length()){
                char ch = file.charAt(idx2);
                if(ch<'0' || ch>'9') break;
                idx2++;
            }
            String number = file.substring(idx,idx2);
            String tail = file.substring(idx2,file.length());
            list[i] = new FileName(head,number,tail,i);
        }
        Arrays.sort(list, (o1,o2)-> {
            int res = o1.head.toLowerCase().compareTo(o2.head.toLowerCase());
            if(res==0){
                res = Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
                if(res==0){
                    return o1.idx-o2.idx;
                }else return res;
            }
            else return res;
        });
        String[] answer = new String[files.length];
        for(int i=0;i<list.length;i++){
            FileName fn = list[i];
            answer[i] = fn.head + fn.number + fn.tail;
        }
        return answer;
    }
}
