class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l=0,r=0,sum=0,min=100001;
        while(l<=r && r<nums.length) {
            sum+=nums[r];
            if(sum==target){
                System.out.println("r="+r+", l="+l);
                min = Math.min(min,r-l+1);
                sum-=nums[l];
                l++;
                r++;
            }else if (sum>target) {
  
                min = Math.min(min,r-l+1);
                sum-=nums[l];
                l++;
                sum-=nums[r];
            }
            else {
                r++;
            }
        }
        return min==100001?0:min;
    }
}