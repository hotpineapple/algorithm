class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        for(int i=1;i<=nums.length;i++) {
            if(isSuccess(i, nums)) {
                result = i;
            } else {
                break;
            }
        }
        
        return result;
    }
    
    private boolean isSuccess(int size,int[] nums) {
        int startIndex = 0;
        int lastIndex = 0;
        int zeroCount = 0;
        int oneCount = 0;
        for(;lastIndex<size-1;lastIndex++) {
            if(nums[lastIndex] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        for(;lastIndex<nums.length;lastIndex++) {
            if(nums[lastIndex] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
            
            if(zeroCount <= 1) {
                return true;
            }
            
            if(nums[startIndex] == 0) {
                zeroCount--;
            } else {
                oneCount--;
            }
            startIndex++;
        }
        
        return false;
    }
}