class Solution {
    public int removeDuplicates(int[] nums) {
        int startIndex = 0;
        int lastIndex = 0;
        int nowNumber = -101;
        int resultCount = 0;
        for(;startIndex<nums.length;startIndex++) {
            if(lastIndex == nums.length) {
                nums[startIndex] = 0;
            }
            for(;lastIndex<nums.length;lastIndex++) {
                if(nowNumber < nums[lastIndex]) {
                    nums[startIndex] = nums[lastIndex];
                    nowNumber = nums[lastIndex];
                    resultCount++;
                    lastIndex++;
                    break;
                }
            }
        }
        return resultCount;
    }
}