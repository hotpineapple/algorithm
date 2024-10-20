class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int aux = nums.length-1;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]%2==1) {
                while(aux>i) {
                    if(nums[aux]%2==0) {
                        int temp = nums[i];
                        nums[i] = nums[aux];
                        nums[aux]= temp;
                        aux--;
                        break;
                    }
                    aux--;
                }
            }
        }
        return nums;
    }
}