class Solution {
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for (int i=0;i<nums.length;i++) {
            int len = Integer.toString(nums[i]).length();
            if (len %2 ==0) cnt++;
        }
        
        return cnt;
    }
}