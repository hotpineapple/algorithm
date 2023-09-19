import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        Map<String,Integer> map1 = new HashMap<>();
        for(int i=0;i<str1.length()-1;i++){
            if(str1.charAt(i) < 'a' || str1.charAt(i) > 'z' ) continue;
            if(str1.charAt(i+1) < 'a' || str1.charAt(i+1) > 'z' ) continue;
            int cnt = map1.getOrDefault(str1.substring(i,i+2),0);
            map1.put(str1.substring(i,i+2), cnt+1);
        }
        Map<String,Integer> map2 = new HashMap<>();
        for(int i=0;i<str2.length()-1;i++){
            if(str2.charAt(i) < 'a' || str2.charAt(i) > 'z' ) continue;
            if(str2.charAt(i+1) < 'a' || str2.charAt(i+1) > 'z' ) continue;
            int cnt = map2.getOrDefault(str2.substring(i,i+2),0);
            map2.put(str2.substring(i,i+2), cnt+1);
        }
        int andCnt = 0; int orCnt=0;
        Set<String> keyset1 = map1.keySet();
        Iterator<String> it1 = keyset1.iterator();
        
        while(it1.hasNext()) {
            String str = it1.next();
            if(map2.getOrDefault(str,0) > 0){
                andCnt += Math.min(map1.get(str),map2.get(str));
                orCnt += Math.max(map1.get(str),map2.get(str));
                map2.remove(str);
            }else{
                orCnt+=map1.get(str);
            }
        }
        Set<String> keyset2 = map2.keySet();
        Iterator<String> it2 = keyset2.iterator();
        while(it2.hasNext()) {
           orCnt+=map2.get(it2.next());
        }
        
        return orCnt == 0 ? 65536 : (int)(((float)andCnt/orCnt)*65536);
    }
}
