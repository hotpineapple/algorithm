class Solution {
    static long N;
    static int Times[];
    public long solution(int n, int[] times) {
        
        N = n;
        Times = times;
        long lo = 1;
        long hi = 10000000000000L;
        long answer = hi;
        while(lo < hi){
            long mid = (lo+hi)/2;
            if(isTrue(mid)){
                answer = mid;
                hi = mid;
            }else{
                lo = mid+1;
            }
        }
        return answer;
    }
    private boolean isTrue(long limit){
        long num = 0;
        for(int i = 0;i<Times.length;i++) num += limit/Times[i];
        
        
        if(num<N) return false;
        return true;
    }
}
