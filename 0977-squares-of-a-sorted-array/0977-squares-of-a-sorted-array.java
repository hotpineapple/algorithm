class Solution {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            if (Math.abs(nums[l]) > Math.abs(nums[r])) {
                res[i] = (int) Math.pow(nums[l], 2);
                l++;
            } else {
                res[i] = (int) Math.pow(nums[r], 2);
                r--;
            }
        }

        return res;
    }
}