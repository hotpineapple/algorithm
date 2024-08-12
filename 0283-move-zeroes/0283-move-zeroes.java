class Solution {
    public void moveZeroes(int[] nums) {
       int zeroCnt=0;
       for (int i=0;i<nums.length;i++){
            if(nums[i]==0) zeroCnt++;
        }
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0) nums[j++]=nums[i];
        }
        for(j=nums.length-zeroCnt;j<nums.length;j++){
            nums[j]=0;
        }

    }
}