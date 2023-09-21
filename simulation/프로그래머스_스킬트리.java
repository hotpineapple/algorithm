import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i=0;i<skill_trees.length;i++){
            String st = skill_trees[i];
            if(isAvailable(skill,st)) answer++;
        }
        return answer;
    }
    private boolean isAvailable(String skill, String st){
        Map<Character, Boolean> map = new HashMap<>();
        for(int i=0;i<st.length();i++){
            char ch = st.charAt(i);
            for(int j=0;j<skill.indexOf(ch+"");j++){
                if(!map.getOrDefault(skill.charAt(j),false)) return false;
            }
            map.put(ch,true);
        }
        return true;
    }
}
