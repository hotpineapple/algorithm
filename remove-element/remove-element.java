class Solution {
    public int removeElement(int[] nums, int val) {
        int cnt=0;
        int len = nums.length;
        int k = len-1;
        for (int i=0;i<len;i++){
            if(nums[i]==val) {
                cnt++;
                for(int j=k;j>i;j--) {
                    if (nums[j]!=val) {
                        nums[i] = nums[j];
                        k=j-1;
                        break;
                    }
                }
            }
        }
        
        return len-cnt;
    }
}