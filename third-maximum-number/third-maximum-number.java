class Solution {
    public int thirdMax(int[] nums) {
        int firstMax=Integer.MIN_VALUE;
        int secondMax=Integer.MIN_VALUE;
        int thirdMax=Integer.MIN_VALUE;
        boolean flag = false;
        for(int i=0;i<nums.length;i++) {
           if(nums[i]>=firstMax) firstMax = nums[i];
        }
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>secondMax && nums[i]<firstMax) secondMax = nums[i];
        }
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>=thirdMax && nums[i]<secondMax) {
                flag = true;
                thirdMax = nums[i];
            }
        }
        
        return flag ?thirdMax:firstMax;
    }
}