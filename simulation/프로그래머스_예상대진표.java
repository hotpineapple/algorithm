class Solution{
    public int solution(int n, int a, int b){
        int max = 0;
        int num = n;
        while(num>1){
            num/=2;
            max++;
        }
        int left = 1; int right = n;
        int mid = (right+left-1)/2;
        int prev=0;
        while(right-left>0){
            int res = sameGroup(mid,a,b);
            if(res==0) break;
            max--;
            if(res==1){
                left=mid+1;
                prev=mid;
            }else if(res==-1){
                left=prev+1;
                right=mid;
            }
            
            mid = (right+left-1)/2;
        }
        
        return max;
    }
    private int sameGroup(int n, int a, int b) {
        if(a<=n && b<=n)  return -1;
        if(a>n && b>n)  return 1;
        return 0;
    }
}
