class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while(n >= k){
            sb.insert(0,n%k+"");
            n/=k;
        }
        sb.insert(0,n);
        
        String str = sb.toString();
        if(str.contains("0")){
            int cnt=0;
            String[] nums = str.split("0");
            for(int i=0;i<nums.length;i++){
                if(isPrime(nums[i])) cnt++;
            }
            return cnt;
        }else {
            if(isPrime(str)) return 1;
            else return 0;
        }
    }
    private boolean isPrime(String strNum) {
        try{
            long num = Long.parseLong(strNum);
            if(num==1) return false;

            for (int i = 2; i<=(int)Math.sqrt(num); i++) {
                if (num % i == 0) {
                   return false;
                }
            }
            return true;
        }catch(RuntimeException e) {
            return false;
        }
        
    }
}
