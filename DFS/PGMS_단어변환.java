class Solution {
    static boolean[] vst;
    static int answer;
    public int solution(String begin, String target, String[] words) {

        vst = new boolean[words.length];
        int i = 0;
        for(; i< words.length; i++)
            if(target.equals(words[i])) break;
        
        if(i == words.length) return 0;
        
        for(i=0;i<words.length;i++){
            if(cmp(begin,words[i])==1) {
                dfs(words[i],i,target,words,1);
                vst[i] = false;
            }
        }
        
        return answer;
    }
    private int cmp(String str1, String str2){
        int cnt = 0;
        for(int i = 0; i<str1.length();i++){
            if(str1.charAt(i)!=str2.charAt(i)) cnt++;
        }
        return cnt;
    }
    private void dfs(String str,int i, String target, String[] words, int cnt){
        
        vst[i] = true;
        
        if(str.equals(target)) {
            answer = cnt;
            return;
        }
        
        for(i=0;i<words.length;i++){
            if(!vst[i] && cmp(str,words[i])==1) {
                dfs(words[i],i,target,words,cnt+1);
                vst[i] = false;
            }
        }
    }
}
